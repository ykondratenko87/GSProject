package by.tms.gsproject.controller.order;

import by.tms.gsproject.api.order.OrderResponse;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class AllOrdersController {
    public void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authenticatedUser");
            if (user != null) {
                OrderService orderService = new OrderService();
                OrderResponse orderResponse = orderService.allOrders(user.getId());
                session.setAttribute("orders", orderResponse);
                session.setAttribute("orderStatus", orderResponse.getStatus());
                Long orderCost = orderService.getOrderCostById(orderResponse.getId());
                req.setAttribute("orderCost", orderCost);
                req.setAttribute("orderId", orderResponse.getId());
                req.getRequestDispatcher("/jsp/client/basket.jsp").forward(req, resp);
            }
        }
    }
}