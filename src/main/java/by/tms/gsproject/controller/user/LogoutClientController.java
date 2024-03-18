package by.tms.gsproject.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutClientController {
    public void logoutClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().invalidate();
    req.getRequestDispatcher("/jsp/auth/login.jsp").forward(req, resp);
}
}