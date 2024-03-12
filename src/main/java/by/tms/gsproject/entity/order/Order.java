package by.tms.gsproject.entity.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long cost;
    private String status;
}