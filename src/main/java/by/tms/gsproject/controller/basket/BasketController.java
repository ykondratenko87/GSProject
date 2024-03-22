package by.tms.gsproject.controller.basket;

import by.tms.gsproject.api.order.OrderResponse;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class BasketController {
    public void addOrderByBasket(HttpServletRequest req, HttpServletResponse resp) {
        String idProduct = req.getParameter("productId");
        String productCount = req.getParameter("count");
        if (idProduct == null || idProduct.isEmpty() || productCount == null || productCount.isEmpty()) {
            req.setAttribute("error", "Invalid product ID or count");
            return;
        }
        try {
            long productId = Long.parseLong(idProduct);
            long count = Long.parseLong(productCount);
            if (productId <= 0 || count <= 0) {
                req.setAttribute("error", "Invalid product ID or count");
                return;
            }
            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    OrderService orderService = new OrderService();
                    orderService.addOrderByBasket(user.getId(), productId, count);
                }
            }
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid product ID or count");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}