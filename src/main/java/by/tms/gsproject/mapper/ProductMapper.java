package by.tms.gsproject.mapper;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.entity.product.ProductType;

public class ProductMapper {
    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setType(ProductType.valueOf(productRequest.getType()));
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setType(product.getType().toString());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        return productResponse;
    }
}