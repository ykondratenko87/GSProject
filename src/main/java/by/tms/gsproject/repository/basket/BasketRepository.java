package by.tms.gsproject.repository.basket;

import by.tms.gsproject.entity.basket.Basket;

import java.sql.SQLException;
import java.util.List;

public interface BasketRepository {
    Basket add(Long orderId, Long productId, Long count) throws SQLException;

    void makeOrder(Long userId) throws SQLException;

    List<Basket> getBasketsByOrderId(Long orderId) throws SQLException;

    void cleanBasket(Long orderId, List<Long> productId, List<Long> count) throws SQLException;

    void clean();
}