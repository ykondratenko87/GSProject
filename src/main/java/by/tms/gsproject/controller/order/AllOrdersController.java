package by.tms.gsproject.controller.order;

import by.tms.gsproject.api.order.OrderResponse;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AllOrdersController {
    public void allOrders(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                OrderService orderService = new OrderService();
                OrderResponse orderResponse = orderService.allOrders(user.getId());
                session.setAttribute("orders", orderResponse);
                try {
                    req.getRequestDispatcher("/jsp/client/products.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}