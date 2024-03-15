package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.repository.product.ProductJDBCRepository;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.product.ProductFileRepository;

public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductJDBCRepository();
    }

    public void addProduct(ProductRequest productRequest) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.toEntity(productRequest);
        productRepository.add(product);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    public ProductResponse getProductById(long productId) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productRepository.findById(productId);
        if (product != null) {
            return productMapper.toResponse(product);
        } else {
            return null;
        }
    }
}