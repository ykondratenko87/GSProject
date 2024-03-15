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
}