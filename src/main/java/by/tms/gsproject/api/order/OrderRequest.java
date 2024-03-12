package by.tms.gsproject.api.order;

import by.tms.gsproject.entity.order.Order;
import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private Long userId;
    private Long cost;
    private Order status;
}