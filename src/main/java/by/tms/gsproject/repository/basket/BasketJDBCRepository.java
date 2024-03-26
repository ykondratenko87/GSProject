package by.tms.gsproject.repository.basket;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.basket.Basket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BasketJDBCRepository implements BasketRepository {
    JDBCConnection connection = new JDBCConnection();

    @Override
    public Basket add(Long orderId, Long productId, Long count) throws SQLException {
        Connection con = connection.getConnection();
        PreparedStatement preparedStatementMax = con.prepareStatement("select max(id) from gsproject.baskets");
        ResultSet resultSet = preparedStatementMax.executeQuery();
        resultSet.next();
        long maxId = resultSet.getLong(1);
        PreparedStatement preparedStatement = con.prepareStatement(" insert into gsproject.baskets(id, orderid ,productid, count)  " + "values (?,?,?,?)");
        preparedStatement.setLong(1, ++maxId);
        preparedStatement.setLong(2, orderId);
        preparedStatement.setLong(3, productId);
        preparedStatement.setLong(4, count);
        preparedStatement.executeUpdate();
        PreparedStatement quantity = con.prepareStatement("SELECT quantity from gsproject.products WHERE id = ?");
        quantity.setLong(1, productId);
        ResultSet executeQuery = quantity.executeQuery();
        executeQuery.next();
        long quantityLong = executeQuery.getLong(1);
        if (quantityLong < 1) {
            throw new RuntimeException("Товар закончился");
        }
        PreparedStatement preparedStatementCount = con.prepareStatement("UPDATE gsproject.products SET quantity = ? where id = ?");
        preparedStatementCount.setLong(1, quantityLong - count);
        preparedStatementCount.setLong(2, productId);
        preparedStatementCount.executeUpdate();
        Basket basket = new Basket();
        basket.setId(maxId);
        basket.setOrderId(orderId);
        basket.setProductId(productId);
        basket.setCount(count);
        timerDeleteOrdersByBasket(orderId, count, productId);
        return basket;
    }

    @Override
    public void makeOrder(Long userId) throws SQLException {
        Connection con = connection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("UPDATE gsproject.orders SET status = ? where userid = ?");
        preparedStatement.setString(1, "COMPLETED");
        preparedStatement.setLong(2, userId);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Basket> getBasketsByOrderId(Long orderId) throws SQLException {
        Connection con = connection.getConnection();
        PreparedStatement preparedStatementMaxId = con.prepareStatement("SELECT * from gsproject.baskets WHERE orderid = ?");
        preparedStatementMaxId.setLong(1, orderId);
        ResultSet resultSet = preparedStatementMaxId.executeQuery();
        List<Basket> baskets = new ArrayList<>();
        while (resultSet.next()) {
            Basket basket = new Basket();
            Long id = resultSet.getLong("id");
            Long orderid = resultSet.getLong("orderid");
            Long productid = resultSet.getLong("productid");
            Long count = resultSet.getLong("count");
            basket.setId(id);
            basket.setOrderId(orderid);
            basket.setProductId(productid);
            basket.setCount(count);
            baskets.add(basket);
        }
        return baskets;
    }

    public void timerDeleteOrdersByBasket(Long orderId, Long count, Long productId) throws SQLException {
        final String tableDeleteBasket = "DELETE FROM gsproject.baskets where orderid = ?";
        final String tableDeleteOrders = "DELETE FROM gsproject.orders where id = ?";
        final String selectCountProduct = "UPDATE gsproject.products SET quantity = ? where id = ?";
        Connection con = connection.getConnection();
        PreparedStatement preparedStatementStatus = con.prepareStatement("select status from gsproject.orders WHERE id = ?");
        preparedStatementStatus.setLong(1, orderId);
        ResultSet status = preparedStatementStatus.executeQuery();
        status.next();
        String statusString = status.getString(1);
        PreparedStatement quantity = con.prepareStatement("SELECT quantity from gsproject.products WHERE id = ?");
        quantity.setLong(1, productId);
        ResultSet executeQuery = quantity.executeQuery();
        executeQuery.next();
        long quantityLong = executeQuery.getLong(1);
        PreparedStatement preparedStatementProduct = con.prepareStatement(selectCountProduct);
        preparedStatementProduct.setLong(1, quantityLong + count);
        preparedStatementProduct.setLong(2, productId);
        PreparedStatement preparedStatementBasket = con.prepareStatement(tableDeleteBasket);
        preparedStatementBasket.setLong(1, orderId);
        PreparedStatement preparedStatementOrders = con.prepareStatement(tableDeleteOrders);
        preparedStatementOrders.setLong(1, orderId);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (statusString.equals("ORDERING") && statusString != null) {
                    try {
                        preparedStatementProduct.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        preparedStatementBasket.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        preparedStatementOrders.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        long time = 7200000L;
        timer.schedule(task, time);
    }

    @Override
    public void cleanBasket(Long orderId, List<Long> productId, List<Long> count) throws SQLException {
        final String tableDeleteBasket = "DELETE FROM gsproject.baskets where orderid = ?";
        final String tableDeleteOrders = "DELETE FROM gsproject.orders where id = ?";
        final String selectCountProduct = "UPDATE gsproject.products SET quantity = ? where id = ?";
        Connection con = connection.getConnection();
        PreparedStatement preparedStatementStatus = con.prepareStatement("select status from gsproject.orders WHERE id = ?");
        preparedStatementStatus.setLong(1, orderId);
        ResultSet status = preparedStatementStatus.executeQuery();
        status.next();
        String statusString = status.getString(1);
        PreparedStatement preparedStatementQuantity = con.prepareStatement("SELECT quantity from gsproject.products WHERE id = ANY(?)");
        Array array = con.createArrayOf("INTEGER", productId.toArray());
        preparedStatementQuantity.setArray(1, array);
        ResultSet resultSet = preparedStatementQuantity.executeQuery();
        List<Integer> quantityList = new ArrayList<>();
        while (resultSet.next()) {
            Integer quantity = resultSet.getInt("quantity");
            quantityList.add(quantity);
        }
        PreparedStatement preparedStatementProduct = con.prepareStatement(selectCountProduct);
        for (int i = 0; i < quantityList.size(); i++) {
            Integer value = quantityList.get(i);
            for (int j = 0; j < count.size(); j++) {
                Long integer = count.get(j);
                for (int k = 0; k < productId.size(); k++) {
                    Long aLong = productId.get(k);
                    preparedStatementProduct.setLong(1, value + integer);
                    preparedStatementProduct.setLong(2, aLong);
                }
            }
        }
        PreparedStatement preparedStatementBasket = con.prepareStatement(tableDeleteBasket);
        preparedStatementBasket.setLong(1, orderId);
        PreparedStatement preparedStatementOrders = con.prepareStatement(tableDeleteOrders);
        preparedStatementOrders.setLong(1, orderId);
        if (statusString.equals("ORDERING") && statusString != null) {
            preparedStatementProduct.executeUpdate();
            preparedStatementBasket.executeUpdate();
            preparedStatementOrders.executeUpdate();
        } else {
            throw new RuntimeException("Корзина пустая");
        }
    }
}