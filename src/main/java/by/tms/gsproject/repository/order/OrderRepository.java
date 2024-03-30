package by.tms.gsproject.repository.order;

import by.tms.gsproject.entity.order.Order;

import java.sql.SQLException;

public interface OrderRepository {
    Order add(Long userId, long productPrice) throws SQLException;

    Order getOrderByUserid(Long userId) throws SQLException;

    Long getCostByOrderId(Long orderId) throws SQLException;

    void updateOrderCost(Long orderId, Long newCost) throws SQLException;
}