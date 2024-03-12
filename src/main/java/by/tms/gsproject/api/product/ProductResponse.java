package by.tms.gsproject.api.product;

import by.tms.gsproject.entity.product.Product;
import lombok.Data;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private String type;
    private double price;
    private int quantity;
}