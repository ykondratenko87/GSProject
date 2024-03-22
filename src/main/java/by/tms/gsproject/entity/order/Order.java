package by.tms.gsproject.entity.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long cost;
    //    private OrderStatus status;
    private String status;

    public Order(Long id, Long userId, String status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }
}