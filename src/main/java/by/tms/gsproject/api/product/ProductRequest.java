package by.tms.gsproject.api.product;

import lombok.Data;

@Data
public class ProductRequest {
    private long id;
    private String name;
    private String type;
    private double price;
    private int quantity;
}