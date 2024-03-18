package by.tms.gsproject.controller.user;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class ShowAllUsersController {
    private final UserService userService;

    public ShowAllUsersController() {
        this.userService = new UserService();
    }

    public void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> users = userService.allUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin/editusers.jsp");
        dispatcher.forward(request, response);
    }
}