package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserRequest;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationController {

    public void registration(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        if (login.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
            req.getRequestDispatcher("jsp/error.jsp");
        }
        UserRequest userRequest = new UserRequest();
        UserService userService = new UserService();
        userRequest.setLogin(login);
        userRequest.setName(name);
        userRequest.setSurname(surname);
        userRequest.setPassword(password);
        userService.register(userRequest);
    }
}