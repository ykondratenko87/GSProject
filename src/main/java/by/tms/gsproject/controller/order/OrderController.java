package by.tms.gsproject.controller.order;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class OrderController {
    public void makeOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authenticatedUser");
            if (user != null) {
                OrderService orderService = new OrderService();
                orderService.makeOrder(user.getId());
                req.getSession().setAttribute("orderStatus", "COMPLETED");
                req.getRequestDispatcher("/jsp/client/basket.jsp").forward(req, resp);
            }
        }
    }
}