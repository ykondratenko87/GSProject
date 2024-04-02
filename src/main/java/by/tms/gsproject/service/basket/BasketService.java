package by.tms.gsproject.service.basket;

import by.tms.gsproject.entity.basket.Basket;
import by.tms.gsproject.entity.order.Order;
import by.tms.gsproject.repository.basket.BasketJDBCRepository;
import by.tms.gsproject.repository.basket.BasketRepository;
import by.tms.gsproject.repository.order.OrderJDBCRepository;
import by.tms.gsproject.repository.order.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class BasketService implements BasketServiceInterface {
    @Override
    public void cleanBasket(Long userId) throws SQLException {
        OrderRepository orderJDBCRepository = new OrderJDBCRepository();
        Order orderByUserid = orderJDBCRepository.getOrderByUserid(userId);
        BasketRepository basketRepository = new BasketJDBCRepository();
        Long getOrderId = orderByUserid.getId();
        List<Basket> basketsByOrderId = basketRepository.getBasketsByOrderId(getOrderId);
        List<Long> listProductId = basketsByOrderId.stream().map(basket -> basket.getProductId()).toList();
        List<Long> listCount = basketsByOrderId.stream().map(Basket::getCount).toList();
        basketRepository.cleanBasket(getOrderId, listProductId, listCount);
    }

    @Override
    public void clean() {
        BasketRepository basketRepository = new BasketJDBCRepository();
        basketRepository.clean();
    }
}