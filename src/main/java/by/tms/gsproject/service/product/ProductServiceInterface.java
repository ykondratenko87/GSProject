package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;

import java.util.Collection;

public interface ProductServiceInterface {
    ProductResponse addProduct(ProductRequest productRequest);

    void deleteProduct(long productId);

    ProductResponse getProductById(long productId);

    Collection<Product> allProducts();

    ProductResponse getProductByName(String productName);
}