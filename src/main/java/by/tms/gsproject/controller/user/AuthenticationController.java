package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole.Role;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationController {
    public void authentication(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);
        UserService userService = new UserService();
        User authenticate = userService.authenticate(userRequest);
        if (authenticate == null) {
            req.getRequestDispatcher("/jsp/auth/registration.jsp").forward(req, resp);
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("authenticatedUser", authenticate);
        assert authenticate != null;
        if (authenticate.getRole().equals(Role.ADMIN)) {
            req.getRequestDispatcher("/jsp/admin/admin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
        }
    }
}