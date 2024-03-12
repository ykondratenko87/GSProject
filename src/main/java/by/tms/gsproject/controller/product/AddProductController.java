package by.tms.gsproject.controller.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.mapper.ProductMapper;
import by.tms.gsproject.repository.product.FileProductRepository;
import by.tms.gsproject.repository.product.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddProductController {
    private final ProductRepository productRepository = new FileProductRepository();
    private final ProductMapper productMapper = new ProductMapper();

    public void addProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(request.getParameter("name"));
        productRequest.setType(request.getParameter("type"));
        productRequest.setPrice(Double.parseDouble(request.getParameter("price")));
        productRequest.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        Product newProduct = productMapper.toEntity(productRequest);
        productRepository.add(newProduct);

        request.setAttribute("message", "Товар успешно добавлен");
        request.getRequestDispatcher("/jsp/admin/product.jsp").forward(request, response);
    }
}