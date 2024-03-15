package by.tms.gsproject.entity.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private long id;
    private String name;
    private String type;
    private double price;
    private int quantity;

    public Product(long id, String name, String type, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
    public Product() {
    }
}