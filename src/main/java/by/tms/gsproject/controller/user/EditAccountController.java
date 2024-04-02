package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class EditAccountController {
    public void showDates(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            req.setAttribute("error", "Пользователь не авторизован");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        User user = (User) session.getAttribute("authenticatedUser");
        if (user == null) {
            req.setAttribute("error", "Пользователь не авторизован");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/client/account.jsp").forward(req, resp);
    }

    public void updateDates(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("authenticatedUser");
        if (user == null) {
            req.setAttribute("error", "Пользователь не авторизован");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        long userId = user.getId();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setId(userId);
        userRequest.setLogin(login);
        userRequest.setName(name);
        userRequest.setSurname(surname);
        userRequest.setPassword(password);
        try {
            userService.updateUserDates(userRequest);
            req.setAttribute("user", userRequest);
            req.getRequestDispatcher("/jsp/client/account.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
    }
}