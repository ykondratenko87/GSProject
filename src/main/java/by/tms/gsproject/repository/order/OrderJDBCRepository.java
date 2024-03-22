package by.tms.gsproject.repository.order;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderJDBCRepository implements OrderRepository {
    JDBCConnection connection = new JDBCConnection();

    @Override
    public Order add(Long userId) {
        try (Connection con = connection.getConnection(); PreparedStatement preparedStatementMaxId = con.prepareStatement("SELECT max(id) from gsproject.orders"); PreparedStatement preparedStatement = con.prepareStatement(" insert into gsproject.orders(id, userId, cost, status) values (?,?,?,?)");) {
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            long maxId = resultSet.getLong(1);
            maxId++;
            String status = "Создан";
            preparedStatement.setLong(1, maxId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setNull(3, java.sql.Types.NULL);
            preparedStatement.setString(4, status);
            preparedStatement.executeUpdate();
            Order order = new Order(maxId, userId, status);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при добавлении заказа");
        }
    }

    @Override
    public Order getOrderByUserid(Long userId) {
        try (Connection con = connection.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM gsproject.orders WHERE userid = ?");) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String status = resultSet.getString("status");
                order = new Order(id, userId, status);
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении заказа по ID пользователя");
        }
    }
}