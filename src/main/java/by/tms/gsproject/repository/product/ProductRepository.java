package by.tms.gsproject.repository.product;

import by.tms.gsproject.entity.product.Product;

import java.util.Collection;

public interface ProductRepository {
    void add(Product product);

    void deleteById(long productId);

    Product findById(long productId);

    Collection<Product> allProducts();

    Product findByName(String productName);
}