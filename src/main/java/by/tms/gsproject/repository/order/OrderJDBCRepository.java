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
    public Order add(Long userId, long productPrice) throws SQLException {
        Connection con = connection.getConnection();
        PreparedStatement preparedStatementMaxId = con.prepareStatement("SELECT max(id) from gsproject.orders");
        ResultSet resultSet = preparedStatementMaxId.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        maxId++;
        PreparedStatement preparedStatement = con.prepareStatement(" insert into gsproject.orders(id, userId, cost, status)  " + "values (?,?,?,?)");
        String status = "ORDERING";
        preparedStatement.setLong(1, maxId);
        preparedStatement.setLong(2, userId);
        preparedStatement.setLong(3, productPrice);
        preparedStatement.setString(4, status);
        preparedStatement.executeUpdate();
        return new Order(maxId, userId, productPrice, status);
    }

    @Override
    public Order getOrderByUserid(Long userId) throws SQLException {
        Connection con = connection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM gsproject.orders WHERE userid = ?");
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
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