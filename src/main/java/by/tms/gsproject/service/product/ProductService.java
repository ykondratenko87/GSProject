package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.product.FileProductRepository;
import by.tms.gsproject.service.product.ProductServiceInterface;

public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository = new FileProductRepository();
    private final ProductMapper productMapper = new ProductMapper();

    public void addProduct(ProductRequest productRequest) {
        Product newProduct = productMapper.toEntity(productRequest);
        productRepository.add(newProduct);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    public ProductResponse getProductById(long productId) {
        Product product = productRepository.findById(productId);
        if (product != null) {
            return productMapper.toResponse(product);
        } else {
            return null;
        }
    }
}