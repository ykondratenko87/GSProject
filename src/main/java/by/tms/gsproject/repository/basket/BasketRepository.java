package by.tms.gsproject.repository.basket;

import by.tms.gsproject.entity.basket.Basket;

import java.util.List;

public interface BasketRepository {
    Basket add(Long orderId, Long productId, Long count);

    void makeOrder(Long userId);

    List<Basket> getBasketsByOrderId(Long orderId);

    default void cleanBasket(Long orderId, List<Long> productId, List<Long> count) {
    }
}