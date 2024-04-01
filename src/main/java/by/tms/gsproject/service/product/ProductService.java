package by.tms.gsproject.service.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.repository.product.ProductJDBCRepository;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.product.ProductFileRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
        return productRepository.allProducts();
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

    public List<ProductResponse> getProductsByIds(List<Long> ids) throws SQLException {
        ProductRepository repository = new ProductJDBCRepository();
        List<Product> products = repository.getProductsByIds(ids);
        if (products.isEmpty()) {
            throw new RuntimeException("Товары не добавлены");
        }
        ProductMapper productMapper = new ProductMapper();
        List<ProductResponse> productResponses = products.stream().map(product -> productMapper.toResponse(product)).toList();
        return productResponses;
    }

    public double getProductPriceById(long productId) {
        ProductRepository repository = new ProductJDBCRepository();
        Product product = repository.findById(productId);
        if (product != null) {
            return product.getPrice();
        } else {
            throw new IllegalArgumentException("Товар с указанным ID не найден");
        }
    }
    public long getProductQuantityById(Long productId) throws SQLException {
        return productRepository.getProductQuantityById(productId);
    }
}