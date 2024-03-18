package by.tms.gsproject.controller.product;

import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class ShowAllProductsController {
    private final ProductService productService;

    public ShowAllProductsController() {
        this.productService = new ProductService();
    }

    public void showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Product> products = productService.allProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin/admin.jsp");
        dispatcher.forward(request, response);
    }
}