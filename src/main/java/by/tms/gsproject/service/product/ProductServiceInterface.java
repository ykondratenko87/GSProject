package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ProductServiceInterface {
    ProductResponse addProduct(ProductRequest productRequest);

    void deleteProduct(long productId);

    ProductResponse getProductById(long productId);

    Collection<Product> allProducts();

    ProductResponse getProductByName(String productName);

    List<ProductResponse> getProductsByIds(List<Long> ids) throws SQLException;

    double getProductPriceById(long productId);

    long getProductQuantityById(Long productId) throws SQLException;
}