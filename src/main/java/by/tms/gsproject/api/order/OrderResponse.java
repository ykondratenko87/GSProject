package by.tms.gsproject.api.order;

import by.tms.gsproject.api.product.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long cost;
    private String status;
    List<ProductResponse> products;
}