package by.tms.gsproject.api.product;

import by.tms.gsproject.entity.product.Product;
import lombok.Data;

@Data
public class ProductRequest {
    private long id;
    private String name;
    private Product type;
    private double price;
    private int quantity;
}