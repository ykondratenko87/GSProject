package by.tms.gsproject.controller.product;

import by.tms.gsproject.entity.product.Product;
import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class ShowProductsClientController {
    private final ProductService productService;

    public ShowProductsClientController() {
        this.productService = new ProductService();
    }

    public void showAProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Product> products = productService.allProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/client/products.jsp").forward(request, response);
    }
}