package by.tms.gsproject.api.order;

import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private Long userId;
    private Long cost;
    private String status;
}