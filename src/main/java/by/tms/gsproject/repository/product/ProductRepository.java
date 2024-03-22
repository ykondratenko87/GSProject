package by.tms.gsproject.repository.product;

import by.tms.gsproject.entity.product.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    void add(Product product);

    void deleteById(long productId);

    Product findById(long productId);

    Collection<Product> allProducts();

    Product findByName(String productName);

    List<Product> getProductsByIds(List<Long> ids);
}