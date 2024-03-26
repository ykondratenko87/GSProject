package by.tms.gsproject.controller.basket;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class CleanBasketController {
    public void cleanBasket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authenticatedUser");
            if (user != null) {
                OrderService orderService = new OrderService();
                orderService.cleanBasket(user.getId());
                session.getAttribute("orders");
                req.getRequestDispatcher("/jsp/client/products.jsp").forward(req, resp);
            }
        }
    }
}