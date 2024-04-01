package by.tms.gsproject.repository.product;

import by.tms.gsproject.config.JDBCConnection;
import by.tms.gsproject.entity.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static by.tms.gsproject.constants.SQLQueries.*;

public class ProductJDBCRepository implements ProductRepository {
    @Override
    public void add(Product product) {
        try (Connection connection = JDBCConnection.getConnection()) {
            try (PreparedStatement checkStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME_AND_TYPE_AND_PRICE)) {
                checkStatement.setString(1, product.getName());
                checkStatement.setString(2, product.getType());
                checkStatement.setDouble(3, product.getPrice());
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    int existingQuantity = resultSet.getInt("quantity");
                    int newQuantity = existingQuantity + product.getQuantity();
                    updateQuantity(connection, id, newQuantity);
                } else {
                    insertNewProduct(connection, product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateQuantity(Connection connection, long id, int newQuantity) throws SQLException {
        try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUANTITY)) {
            updateStatement.setInt(1, newQuantity);
            updateStatement.setLong(2, id);
            updateStatement.executeUpdate();
        }
    }

    private void insertNewProduct(Connection connection, Product product) throws SQLException {
        try (PreparedStatement preparedStatementMax = connection.prepareStatement(MAX_ID)) {
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            long maxId = resultSet.getLong(1);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(long productId) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product with ID: " + productId, e);
        }
    }

    @Override
    public Product findById(long productId) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");
                    return new Product(id, name, type, price, quantity);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed to find product with ID: " + productId, e);
        }
    }

    @Override
    public Collection<Product> allProducts() {
        Collection<Product> allProducts = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String type = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int quantity = resultSet.getInt(5);
                Product product = new Product(id, name, type, price, quantity);
                allProducts.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allProducts;
    }

    @Override
    public Product findByName(String productName) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)) {
            preparedStatement.setString(1, productName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");
                    return new Product(id, name, type, price, quantity);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find product with name: " + productName, e);
        }
    }

    public List<Product> getProductsByIds(List<Long> ids) throws SQLException {
        List<Product> products = new ArrayList<>();
        JDBCConnection connection = new JDBCConnection();
        Connection con = connection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_PRODUCTS_BY_IDS);
        Array array = con.createArrayOf("NUMERIC", ids.toArray());
        preparedStatement.setArray(1, array);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            double price = resultSet.getInt("price");
            int quantity = resultSet.getInt("quantity");
            Product product = new Product(Long.valueOf(id), type, name, price, quantity);
            products.add(product);
        }
        return products;
    }

    @Override
    public long getProductQuantityById(Long productId) throws SQLException {
        JDBCConnection connection = new JDBCConnection();
        Connection con = connection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_PRODUCT_QUANTITY_BY_ID);
        preparedStatement.setLong(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong("quantity");
        } else {
            throw new RuntimeException("Товар не найден");
        }
    }
}