package by.tms.gsproject.service.order;

import by.tms.gsproject.api.basket.BasketResponse;
import by.tms.gsproject.api.order.OrderResponse;

import java.sql.SQLException;

public interface OrderServiceInterface {

    OrderResponse addUserByOrder(Long userId, Long productPrice) throws SQLException;

    BasketResponse addOrderByBasket(Long userId, Long productId, Long productPrice, Long count) throws SQLException;

    void makeOrder(Long userId) throws SQLException;

    OrderResponse allOrders(Long userId) throws SQLException;

    Long getOrderCostById(Long orderId) throws SQLException;
}