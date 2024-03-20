package by.tms.gsproject.controller.product;

import by.tms.gsproject.api.product.ProductRequest;
import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddProductController {
    private final ProductService productService;

    public AddProductController() {
        this.productService = new ProductService();
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(request.getParameter("name"));
        productRequest.setType(request.getParameter("type"));
        productRequest.setPrice(Double.parseDouble(request.getParameter("price")));
        productRequest.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        productService.addProduct(productRequest);
        request.setAttribute("message", "Товар успешно добавлен");
        request.getRequestDispatcher("/jsp/admin/addproduct.jsp").forward(request, response);
    }
}