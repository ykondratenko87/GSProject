package by.tms.gsproject.controller.user;

import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteUserController {
    private final UserService userService;

    public DeleteUserController() {
        this.userService = new UserService();
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter("deleteUserId"));
            userService.deleteUserById(userId);
            request.setAttribute("message", "Пользователь успешно удален");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Некорректный User ID");
        }
        request.getRequestDispatcher("/jsp/admin/deleteuser.jsp").forward(request, response);
    }
}