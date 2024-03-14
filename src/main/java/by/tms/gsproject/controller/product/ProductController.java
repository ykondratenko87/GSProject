package by.tms.gsproject.controller.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.repository.product.ProductRepository;
import by.tms.gsproject.repository.product.FileProductRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class ProductController {
    private final ProductRepository productRepository = new FileProductRepository();
    private final ProductMapper productMapper = new ProductMapper();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("search".equals(action)) {
            String productId = request.getParameter("productId");
            Product product = productRepository.findById(Long.parseLong(productId));
            if (product != null) {
                ProductResponse productResponse = productMapper.toResponse(product);
                request.setAttribute("product", productResponse);
            } else {
                request.setAttribute("searchResult", "Товар с ID " + productId + " не найден");
            }
        } else if ("add".equals(action)) {
            ProductRequest productRequest = new ProductRequest();
            productRequest.setName(request.getParameter("name"));
            productRequest.setType(request.getParameter("type"));
            productRequest.setPrice(Double.parseDouble(request.getParameter("price")));
            productRequest.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            Product newProduct = productMapper.toEntity(productRequest);
            productRepository.add(newProduct);
            request.setAttribute("message", "Товар успешно добавлен");
            request.getRequestDispatcher("/jsp/admin/product.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String productId = request.getParameter("deleteProductId");
            if (productId != null && !productId.isEmpty()) {
                productRepository.deleteById(Long.parseLong(productId));
                request.setAttribute("message", "Товар успешно удален");
            } else {
                request.setAttribute("message", "Не удалось удалить товар. Некорректный ID.");
            }
        }

        request.getRequestDispatcher("/jsp/product.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Product> productList = productRepository.allProducts();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/jsp/product.jsp").forward(request, response);
    }
}