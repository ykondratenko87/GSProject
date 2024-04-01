package by.tms.gsproject.service.basket;

import java.sql.SQLException;

public interface BasketServiceInterface {
    void cleanBasket(Long userId) throws SQLException;
    void clean();
}