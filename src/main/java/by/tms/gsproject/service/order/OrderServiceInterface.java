package by.tms.gsproject.service.order;

import by.tms.gsproject.api.basket.BasketResponse;
import by.tms.gsproject.api.order.OrderResponse;

public interface OrderServiceInterface {
    OrderResponse addUserByOrder(Long userId);

    BasketResponse addOrderByBasket(Long userId, Long productId, Long count);

    void makeOrder(Long userId);

    OrderResponse allOrders(Long userId);
}