package by.tms.gsproject.repository.order;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.tms.gsproject.constants.SQLQueries.*;

public class OrderJDBCRepository implements OrderRepository {

    @Override
    public Order add(Long userId, long productPrice) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatementMaxId = con.prepareStatement(SELECT_MAX_ID_QUERY); PreparedStatement preparedStatement = con.prepareStatement(INSERT_ORDER_QUERY)) {
            try (ResultSet resultSet = preparedStatementMaxId.executeQuery()) {
                resultSet.next();
                long maxId = resultSet.getLong(1) + 1;
                String status = "ORDERING";
                preparedStatement.setLong(1, maxId);
                preparedStatement.setLong(2, userId);
                preparedStatement.setLong(3, productPrice);
                preparedStatement.setString(4, status);
                preparedStatement.executeUpdate();
                return new Order(maxId, userId, productPrice, status);
            }
        }
    }

    @Override
    public Order getOrderByUserid(Long userId) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SELECT_ORDER_BY_USER_ID_QUERY)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Order order = new Order();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    Long userid = resultSet.getLong("userid");
                    Long productPrice = resultSet.getLong("cost");
                    String status = resultSet.getString("status");
                    order.setId(id);
                    order.setUserId(userid);
                    order.setProductPrice(productPrice);
                    order.setStatus(status);
                }
                return order;
            }
        }
    }

    public Long getCostByOrderId(Long orderId) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SELECT_COST_BY_ORDER_ID_QUERY)) {
            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Long orderCost = null;
                if (resultSet.next()) {
                    orderCost = resultSet.getLong("cost");
                }
                return orderCost;
            }
        }
    }

    @Override
    public void updateOrderCost(Long orderId, Long newCost) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ORDER_COST_QUERY)) {
            preparedStatement.setLong(1, newCost);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        }
    }
}