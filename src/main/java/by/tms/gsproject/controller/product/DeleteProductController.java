package by.tms.gsproject.controller.product;

import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteProductController {
    private final ProductService productService;

    public DeleteProductController() {
        this.productService = new ProductService();
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long productId = Long.parseLong(request.getParameter("deleteProductId"));
            productService.deleteProduct(productId);
            request.setAttribute("message", "Товар успешно удален");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Некорректный Product ID");
        }
        request.getRequestDispatcher("/jsp/admin/deleteproduct.jsp").forward(request, response);
    }
}