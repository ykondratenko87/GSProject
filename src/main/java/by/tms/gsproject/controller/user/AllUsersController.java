package by.tms.gsproject.controller.user;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.repository.user.UserFileRepository;
import by.tms.gsproject.repository.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class AllUsersController {
    private static final long serialVersionUID = 581366370685874174L;
    private final UserRepository repository;

    public AllUsersController() {
        this.repository = new UserFileRepository();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editUserId = req.getParameter("editUserId");
        if (editUserId != null) {
            long userId = Long.parseLong(editUserId);
            User userToEdit = repository.getUserById(userId);
            if (userToEdit != null) {
                req.setAttribute("userToEdit", userToEdit);
            } else {
                req.setAttribute("message", "Пользователь с указанным ID не найден.");
            }
        }
        Collection<User> allUsers = repository.allUsers();
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/jsp/allusers.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String userId = req.getParameter("deleteUserId");
            if (userId != null && !userId.isEmpty()) {
                repository.deleteById(Long.parseLong(userId));
                req.setAttribute("message", "User успешно удален");
            } else {
                req.setAttribute("message", "Не удалось удалить user. Некорректный ID.");
            }

        } else if ("search".equals(action)) {
            String userId = req.getParameter("userId");
            User user = repository.getUserById(Long.parseLong(userId));
            req.setAttribute("user", user);
        } else if ("showAll".equals(action)) {
            Collection<User> allUsers = repository.allUsers();
            req.setAttribute("users", allUsers);


        } else if ("edit".equals(action)) {
            String userIdString = req.getParameter("editUserId");
            long userId = Long.parseLong(userIdString);
            User userToEdit = repository.getUserById(userId);
            if (userToEdit != null) {
                req.setAttribute("userToEdit", userToEdit);
            } else {
                req.setAttribute("message", "Пользователь с указанным ID не найден.");
            }
        }
        req.setAttribute("users", repository.allUsers());
        req.getRequestDispatcher("/jsp/allusers.jsp").forward(req, resp);
    }
}