package by.tms.gsproject.controller.product;

import by.tms.gsproject.api.product.ProductResponse;
import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SearchProductController {
    private final ProductService productService;

    public SearchProductController() {
        this.productService = new ProductService();
    }

    public void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productName = request.getParameter("productName");
            String productIdStr = request.getParameter("productId");
            if (productName != null && !productName.isEmpty()) {
                ProductResponse productResponse = productService.getProductByName(productName);
                if (productResponse != null) {
                    request.setAttribute("product", productResponse);
                } else {
                    request.setAttribute("searchResult", "Продукт с указанным именем не найден");
                }
            } else if (productIdStr != null && !productIdStr.isEmpty()) {
                long productId = Long.parseLong(productIdStr);
                ProductResponse productResponse = productService.getProductById(productId);
                if (productResponse != null) {
                    request.setAttribute("product", productResponse);
                } else {
                    request.setAttribute("searchResult", "Продукт с указанным ID не найден");
                }
            } else {
                request.setAttribute("searchResult", "Введите имя продукта или ID для поиска");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("searchResult", "Некорректный формат ID продукта");
        }
        request.getRequestDispatcher("/jsp/admin/searchproduct.jsp").forward(request, response);
    }
}