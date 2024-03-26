package by.tms.gsproject.controller.basket;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import by.tms.gsproject.service.product.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class BasketController {
    public void addOrderByBasket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String idProduct = req.getParameter("idProduct");
        String productCount = req.getParameter("ProductCount");
        if (idProduct == null || idProduct.isEmpty() || productCount == null || productCount.isEmpty()) {
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authenticatedUser");
            if (user != null) {
                OrderService orderService = new OrderService();
                ProductService productService = new ProductService();
                long productPrice = (long) productService.getProductPriceById(Long.valueOf(idProduct));
                orderService.addOrderByBasket(user.getId(), Long.valueOf(idProduct), productPrice, Long.valueOf(productCount));
                req.getRequestDispatcher("/jsp/client/products.jsp").forward(req, resp);
                return;
            }
        }
        req.setAttribute("error", "User not logged in");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}