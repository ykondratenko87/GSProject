package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;

public interface ProductServiceInterface {
    void addProduct(ProductRequest productRequest);

    void deleteProduct(long productId);

    ProductResponse getProductById(long productId);
}