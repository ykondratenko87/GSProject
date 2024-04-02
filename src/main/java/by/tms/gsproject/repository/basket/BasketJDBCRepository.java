package by.tms.gsproject.repository.basket;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.basket.Basket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.tms.gsproject.constants.SQLQueries.*;

public class BasketJDBCRepository implements BasketRepository {
    @Override
    public Basket add(Long orderId, Long productId, Long count) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatementMax = con.prepareStatement(SELECT_MAX_BASKET_ID); ResultSet resultSet = preparedStatementMax.executeQuery(); PreparedStatement preparedStatement = con.prepareStatement(INSERT_INTO_BASKETS); PreparedStatement quantity = con.prepareStatement(SELECT_PRODUCT_QUANTITY)) {
            resultSet.next();
            long maxId = resultSet.getLong(1);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.setLong(3, productId);
            preparedStatement.setLong(4, count);
            preparedStatement.executeUpdate();
            quantity.setLong(1, productId);
            try (ResultSet executeQuery = quantity.executeQuery()) {
                executeQuery.next();
                long quantityLong = executeQuery.getLong(1);
                if (quantityLong < 1) {
                    throw new RuntimeException("Товар закончился");
                }
            }
            Basket basket = new Basket();
            basket.setId(maxId);
            basket.setOrderId(orderId);
            basket.setProductId(productId);
            basket.setCount(count);
            return basket;
        }
    }

    @Override
    public void makeOrder(Long userId) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement updateOrderStatusStatement = con.prepareStatement(UPDATE_ORDER_STATUS); PreparedStatement selectProductsAndQuantitiesStatement = con.prepareStatement(SELECT_PRODUCTS_AND_QUANTITIES); PreparedStatement updateProductQuantitiesStatement = con.prepareStatement(UPDATE_PRODUCT_QUANTITIES)) {
            updateOrderStatusStatement.setString(1, "COMPLETED");
            updateOrderStatusStatement.setLong(2, userId);
            updateOrderStatusStatement.executeUpdate();
            selectProductsAndQuantitiesStatement.setLong(1, userId);
            try (ResultSet resultSet = selectProductsAndQuantitiesStatement.executeQuery()) {
                while (resultSet.next()) {
                    long productId = resultSet.getLong("productid");
                    long quantity = resultSet.getLong("count");
                    updateProductQuantitiesStatement.setLong(1, quantity);
                    updateProductQuantitiesStatement.setLong(2, productId);
                    updateProductQuantitiesStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public List<Basket> getBasketsByOrderId(Long orderId) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatementMaxId = con.prepareStatement(SELECT_BASKETS_BY_ORDER_ID)) {
            preparedStatementMaxId.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatementMaxId.executeQuery()) {
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
        }
    }

    @Override
    public void cleanBasket(Long orderId, List<Long> productId, List<Long> count) throws SQLException {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatementStatus = con.prepareStatement(SELECT_ORDER_STATUS); PreparedStatement preparedStatementQuantity = con.prepareStatement(SELECT_PRODUCT_QUANTITIES); PreparedStatement preparedStatementBasket = con.prepareStatement(DELETE_FROM_BASKETS); PreparedStatement preparedStatementOrders = con.prepareStatement(DELETE_FROM_ORDERS)) {
            preparedStatementStatus.setLong(1, orderId);
            try (ResultSet status = preparedStatementStatus.executeQuery()) {
                status.next();
                String statusString = status.getString(1);
                Array array = con.createArrayOf("INTEGER", productId.toArray());
                preparedStatementQuantity.setArray(1, array);
                try (ResultSet resultSet = preparedStatementQuantity.executeQuery()) {
                    List<Integer> quantityList = new ArrayList<>();
                    while (resultSet.next()) {
                        Integer quantity = resultSet.getInt("quantity");
                        quantityList.add(quantity);
                    }
                }
                if (statusString.equals("ORDERING") && statusString != null) {
                    preparedStatementBasket.setLong(1, orderId);
                    preparedStatementBasket.executeUpdate();
                    preparedStatementOrders.setLong(1, orderId);
                    preparedStatementOrders.executeUpdate();
                } else {
                    throw new RuntimeException("Корзина пустая");
                }
            }
        }
    }

    @Override
    public void clean() {
        try (Connection con = JDBCConnection.getConnection(); PreparedStatement preparedStatementBasket = con.prepareStatement(DELETE_ALL_FROM_BASKETS); PreparedStatement preparedStatementOrders = con.prepareStatement(DELETE_ALL_FROM_ORDERS)) {
            preparedStatementBasket.executeUpdate();
            preparedStatementOrders.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}