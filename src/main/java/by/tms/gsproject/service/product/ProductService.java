package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.repository.product.ProductJDBCRepository;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.product.ProductFileRepository;

import java.util.Collection;

public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductJDBCRepository();
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.toEntity(productRequest);
        productRepository.add(product);
        return new ProductResponse();
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse getProductById(long productId) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productRepository.findById(productId);
        if (product != null) {
            return productMapper.toResponse(product);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Product> allProducts() {
        return null;
    }

    @Override
    public ProductResponse getProductByName(String productName) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productRepository.findByName(productName);
        if (product != null) {
            return productMapper.toResponse(product);
        } else {
            return null;
        }
    }
}