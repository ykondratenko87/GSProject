package by.tms.gsproject.service.order;

import by.tms.gsproject.api.basket.BasketResponse;
import by.tms.gsproject.api.order.OrderResponse;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.basket.Basket;
import by.tms.gsproject.entity.order.Order;
import by.tms.gsproject.mapper.BasketMapper;
import by.tms.gsproject.mapper.OrderMapper;
import by.tms.gsproject.repository.basket.BasketJDBCRepository;
import by.tms.gsproject.repository.basket.BasketRepository;
import by.tms.gsproject.repository.order.OrderJDBCRepository;
import by.tms.gsproject.repository.order.OrderRepository;
import by.tms.gsproject.service.product.ProductService;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements OrderServiceInterface {
    @Override
    public OrderResponse addUserByOrder(Long userId, Long productPrice) throws SQLException {
        OrderRepository repository = new OrderJDBCRepository();
        OrderMapper orderMapper = new OrderMapper();
        Order order = repository.add(userId, productPrice);
        return orderMapper.toResponse(order);
    }

    @Override
    public BasketResponse addOrderByBasket(Long userId, Long productId, Long productPrice, Long count) throws SQLException {
        ProductService productService = new ProductService();
        long availableQuantity = productService.getProductQuantityById(productId);
        if (availableQuantity <= 0) {
            throw new IllegalArgumentException("Товар закончился");
        } else if (count > availableQuantity) {
            throw new IllegalArgumentException("Недостаточное количество товара в наличии");
        }

        OrderRepository orderRepository = new OrderJDBCRepository();
        Order orderByUserId = orderRepository.getOrderByUserid(userId);
        if (orderByUserId.getId() == null || !orderByUserId.getStatus().equals("ORDERING")) {
            addUserByOrder(userId, productPrice * count);
            orderByUserId = orderRepository.getOrderByUserid(userId);
        } else {
            orderRepository.updateOrderCost(orderByUserId.getId(), orderByUserId.getProductPrice() + productPrice * count);
        }
        Long orderId = orderByUserId.getId();
        BasketRepository repository = new BasketJDBCRepository();
        if (orderId == null || productId == 0 || count == 0) {
            throw new RuntimeException("Неправильные значения в полях");
        }
        Basket basket = repository.add(orderId, productId, count);
        BasketMapper basketMapper = new BasketMapper();
        return basketMapper.toResponse(basket);
    }

    @Override
    public void makeOrder(Long userId) throws SQLException {
        BasketRepository repository = new BasketJDBCRepository();
        repository.makeOrder(userId);
    }

    @Override
    public OrderResponse allOrders(Long userId) throws SQLException {
        OrderRepository orderJDBCRepository = new OrderJDBCRepository();
        Order orderByUserid = orderJDBCRepository.getOrderByUserid(userId);
        BasketRepository basketRepository = new BasketJDBCRepository();
        Long getOrderId = 0L;
        if (orderByUserid.getStatus().equals("ORDERING")) {
            getOrderId = orderByUserid.getId();
        }
        if (getOrderId == null) {
            throw new RuntimeException("Корзина пустая");
        }
        List<Basket> basketsByOrderId = basketRepository.getBasketsByOrderId(getOrderId);
        if (basketsByOrderId.isEmpty()) {
            throw new RuntimeException("Корзина пустая");
        }
        List<Long> listProductId = basketsByOrderId.stream().map(basket -> basket.getProductId()).toList();
        ProductService productService = new ProductService();
        List<ProductResponse> productsByIds = productService.getProductsByIds(listProductId);
        OrderMapper orderMapper = new OrderMapper();
        OrderResponse orderResponse = orderMapper.toResponse(orderByUserid);
        orderResponse.setProducts(productsByIds);
        return orderResponse;
    }

    @Override
    public Long getOrderCostById(Long orderId) throws SQLException {
        OrderRepository repository = new OrderJDBCRepository();
        return repository.getCostByOrderId(orderId);
    }
}