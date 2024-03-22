package by.tms.gsproject.repository.order;

import by.tms.gsproject.entity.order.Order;

public interface OrderRepository {
    Order add(Long userId);

    Order getOrderByUserid(Long userId);
}