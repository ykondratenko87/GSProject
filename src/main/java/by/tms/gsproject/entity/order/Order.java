package by.tms.gsproject.entity.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long userId;
    private long productPrice;
    private String status;

    public Order() {
    }

    public Order(Long id, Long userId, long productPrice, String status) {
        this.id = id;
        this.userId = userId;
        this.productPrice = productPrice;
        this.status = status;
    }
}