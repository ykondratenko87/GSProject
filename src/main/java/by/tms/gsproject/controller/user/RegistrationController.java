package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationController {

    public void registration(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        if (login.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setLogin(login);
        userRequest.setName(name);
        userRequest.setSurname(surname);
        userRequest.setPassword(password);
        try {
            userService.register(userRequest);
            req.getRequestDispatcher("/jsp/client/client.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "Такой пользователь уже существует!");
            req.getRequestDispatcher("/jsp/exception/error.jsp").forward(req, resp);
        }
    }
}