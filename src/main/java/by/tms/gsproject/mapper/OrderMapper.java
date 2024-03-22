package by.tms.gsproject.mapper;

import by.tms.gsproject.api.order.OrderResponse;
import by.tms.gsproject.entity.order.Order;

public class OrderMapper {
    public OrderResponse toResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }
}