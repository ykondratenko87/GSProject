package by.tms.gsproject.entity.basket;

import lombok.Data;

@Data
public class Basket {
    private Long id;
    private Long productId;
    private Long orderId;
    private Long count;
}