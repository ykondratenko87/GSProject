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

import java.util.List;

public class OrderService implements OrderServiceInterface {
    @Override
    public OrderResponse addUserByOrder(Long userId) {
        OrderRepository repository = new OrderJDBCRepository();
        OrderMapper orderMapper = new OrderMapper();
        Order order = repository.add(userId);
        return orderMapper.toResponse(order);
    }

    @Override
    public BasketResponse addOrderByBasket(Long userId, Long productId, Long count) {
        OrderRepository orderRepository = new OrderJDBCRepository();
        Order orderByUserid = orderRepository.getOrderByUserid(userId);
        Long id = 0L;
        if (orderByUserid.getUserId() == userId && orderByUserid.getUserId() != null && orderByUserid.getStatus().equals("Создан")) {
            id = orderByUserid.getId();
        } else {
            OrderResponse orderResponse = addUserByOrder(userId);
            id = orderResponse.getId();
        }
        BasketRepository repository = new BasketJDBCRepository();
        if (id == 0 || productId == 0 || count == 0) {
            throw new RuntimeException("Неверные значения в полях");
        }
        Basket basket = repository.add(id, productId, count);

        BasketMapper basketMapper = new BasketMapper();
        return basketMapper.toResponse(basket);
    }

    @Override
    public void makeOrder(Long userId) {
        BasketRepository repository = new BasketJDBCRepository();
        repository.makeOrder(userId);
    }

    @Override
    public OrderResponse allOrders(Long userId) {
        OrderRepository orderJDBCRepository = new OrderJDBCRepository();
        Order orderByUserid = orderJDBCRepository.getOrderByUserid(userId);
        BasketRepository basketRepository = new BasketJDBCRepository();
        Long getOrderId = 0L;
        if (orderByUserid.getStatus().equals("Создан")) {
            getOrderId = orderByUserid.getId();
        }
        if (getOrderId == null) {
            throw new RuntimeException("Корзина пустая");
        }
        List<Basket> basketsByOrderId = basketRepository.getBasketsByOrderId(getOrderId);
        List<Long> listProductId = basketsByOrderId.stream().map(basket -> basket.getProductId()).toList();
        ProductService productService = new ProductService();
        List<ProductResponse> productsByIds = productService.getProductsByIds(listProductId);
        OrderMapper orderMapper = new OrderMapper();
        OrderResponse orderResponse = orderMapper.toResponse(orderByUserid);
        orderResponse.setProducts(productsByIds);
        return orderResponse;
    }
}