package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole.Role;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationController {
    public void authentication(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            resp.sendRedirect("/GSProject/jsp/error.jsp");
            return;
        }
        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);
        UserService userService = new UserService();
        User authenticate = userService.authenticate(userRequest);
        if (authenticate == null) {
            resp.sendRedirect("/GSProject/jsp/registration.jsp");
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("authenticatedUser", authenticate);
        if (authenticate.getRole().equals(Role.ADMIN)) {
            resp.sendRedirect("/GSProject/jsp/admin/admin.jsp");
        } else {
            resp.sendRedirect("/GSProject/jsp/client/client.jsp");
        }
    }
}