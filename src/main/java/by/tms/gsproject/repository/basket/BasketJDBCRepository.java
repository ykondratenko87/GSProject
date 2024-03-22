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
    public Basket add(Long orderId, Long productId, Long count) {
        try (Connection connect = connection.getConnection(); PreparedStatement preparedStatementMax = connect.prepareStatement("select max(id) from gsproject.baskets"); ResultSet resultSet = preparedStatementMax.executeQuery()) {
            resultSet.next();
            long maxId = resultSet.getLong(1);
            try (PreparedStatement preparedStatement = connect.prepareStatement(" insert into gsproject.baskets(id, productid, orderid, count) values (?,?,?,?)")) {
                preparedStatement.setLong(1, ++maxId);
                preparedStatement.setLong(2, orderId);
                preparedStatement.setLong(3, productId);
                preparedStatement.setLong(4, count);
                preparedStatement.executeUpdate();
            }
            PreparedStatement quantity = connect.prepareStatement("SELECT quantity from gsproject.products WHERE id = ?");
            quantity.setLong(1, productId);
            ResultSet executeQuery = quantity.executeQuery();
            executeQuery.next();
            long quantityLong = executeQuery.getLong(1);
            if (quantityLong < 1) {
                throw new RuntimeException("Товар закончился");
            }
            PreparedStatement preparedStatementCount = connect.prepareStatement("UPDATE gsproject.products SET quantity = ? where id = ?");
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при добавлении товара в корзину");
        }
    }

    @Override
    public void makeOrder(Long userId) {
        try (Connection connect = connection.getConnection(); PreparedStatement preparedStatement = connect.prepareStatement("UPDATE gsproject.orders SET status = ? where userid = ?")) {
            preparedStatement.setString(1, "Оформлен");
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при оформлении заказа");
        }
    }

    @Override
    public List<Basket> getBasketsByOrderId(Long orderId) {
        try (Connection connect = connection.getConnection(); PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM gsproject.baskets WHERE orderid = ?");) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Basket> baskets = new ArrayList<>();
            while (resultSet.next()) {
                Basket basket = new Basket();
                basket.setId(resultSet.getLong("id"));
                basket.setOrderId(resultSet.getLong("orderid"));
                basket.setProductId(resultSet.getLong("productid"));
                basket.setCount(resultSet.getLong("count"));
                baskets.add(basket);
            }
            return baskets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении корзин по ID заказа");
        }
    }

    public void timerDeleteOrdersByBasket(Long orderId, Long count, Long productId) {
        try (Connection connect = connection.getConnection(); PreparedStatement preparedStatementStatus = connect.prepareStatement("select status from gsproject.orders WHERE id = ?"); PreparedStatement preparedStatementProduct = connect.prepareStatement("SELECT quantity from gsproject.products WHERE id = ?"); PreparedStatement preparedStatementBasket = connect.prepareStatement("DELETE FROM gsproject.baskets where orderid = ?"); PreparedStatement preparedStatementOrders = connect.prepareStatement("DELETE FROM gsproject.orders where id = ?");) {
            preparedStatementStatus.setLong(1, orderId);
            ResultSet status = preparedStatementStatus.executeQuery();
            status.next();
            String statusString = status.getString(1);
            preparedStatementProduct.setLong(1, productId);
            ResultSet executeQuery = preparedStatementProduct.executeQuery();
            executeQuery.next();
            long quantityLong = executeQuery.getLong(1);
            if (statusString.equals("Создан") && statusString != null) {
                preparedStatementProduct.executeUpdate();
                preparedStatementBasket.setLong(1, orderId);
                preparedStatementBasket.executeUpdate();
                preparedStatementOrders.setLong(1, orderId);
                preparedStatementOrders.executeUpdate();
            }
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (statusString.equals("Создан") && statusString != null) {
                        try {
                            preparedStatementProduct.executeUpdate();
                            preparedStatementBasket.executeUpdate();
                            preparedStatementOrders.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            long time = 7200000L;
            timer.schedule(task, time);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при удалении заказов по таймеру");
        }
    }

    @Override
    public void cleanBasket(Long orderId, List<Long> productId, List<Long> count) {
        final String tableDeleteBasket = "DELETE FROM gsproject.baskets where orderid = ?";
        final String tableDeleteOrders = "DELETE FROM gsproject.orders where id = ?";
        final String selectCountProduct = "UPDATE gsproject.products SET quantity = ? where id = ?";
        try (Connection connect = connection.getConnection(); PreparedStatement preparedStatementStatus = connect.prepareStatement("select status from gsproject.orders WHERE id = ?"); PreparedStatement preparedStatementQuantity = connect.prepareStatement("SELECT quantity from gsproject.products WHERE id = ?"); PreparedStatement preparedStatementProduct = connect.prepareStatement(selectCountProduct); PreparedStatement preparedStatementBasket = connect.prepareStatement(tableDeleteBasket); PreparedStatement preparedStatementOrders = connect.prepareStatement(tableDeleteOrders);) {
            preparedStatementStatus.setLong(1, orderId);
            ResultSet status = preparedStatementStatus.executeQuery();
            status.next();
            String statusString = status.getString(1);
            ResultSet resultSet = null;
            for (int i = 0; i < productId.size(); i++) {
                Long product = productId.get(i);
                preparedStatementQuantity.setLong(1, product);
                resultSet = preparedStatementQuantity.executeQuery();
                resultSet.next();
                long quantity = resultSet.getLong("quantity");
                quantity += count.get(i);
                preparedStatementProduct.setLong(1, quantity);
                preparedStatementProduct.setLong(2, product);
                preparedStatementProduct.executeUpdate();
            }
            if (statusString.equals("Создан") && statusString != null) {
                preparedStatementBasket.setLong(1, orderId);
                preparedStatementBasket.executeUpdate();
                preparedStatementOrders.setLong(1, orderId);
                preparedStatementOrders.executeUpdate();
            } else {
                throw new RuntimeException("Корзина пустая, сформируйте заказ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при очистке корзины");
        }
    }
}