package by.tms.gsproject.controller.user;

import by.tms.gsproject.api.user.UserResponse;
import by.tms.gsproject.service.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SearchUserController {
    private final UserService userService;

    public SearchUserController() {
        this.userService = new UserService();
    }

    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userLogin = request.getParameter("userLogin");
            String userIdStr = request.getParameter("userId");
            if (userLogin != null && !userLogin.isEmpty()) {
                UserResponse userResponse = userService.getUserByLogin(userLogin);
                if (userResponse != null) {
                    request.setAttribute("user", userResponse);
                } else {
                    request.setAttribute("searchResult", "Продукт с указанным именем не найден");
                }
            } else if (userIdStr != null && !userIdStr.isEmpty()) {
                long useId = Long.parseLong(userIdStr);
                UserResponse userResponse = userService.getUserById(useId);
                if (userResponse != null) {
                    request.setAttribute("user", userResponse);
                } else {
                    request.setAttribute("searchResult", "Пользователь с указанным ID не найден");
                }
            } else {
                request.setAttribute("searchResult", "Введите логин пользователя или ID для поиска");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("searchResult", "Некорректный формат ID пользователя");
        }
        request.getRequestDispatcher("/jsp/admin/searchuser.jsp").forward(request, response);
    }
}