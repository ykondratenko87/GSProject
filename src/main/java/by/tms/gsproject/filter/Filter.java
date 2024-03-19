package by.tms.gsproject.filter;

import by.tms.gsproject.entity.user.User;
import by.tms.gsproject.entity.user.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getServletPath();
        if (!path.startsWith("/jsp/admin/") && !path.startsWith("/jsp/client/")) {
            chain.doFilter(request, response);
            return;
        }
        User authenticatedUser = (User) httpRequest.getSession().getAttribute("authenticatedUser");
        if (authenticatedUser == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/auth/login.jsp");
            return;
        }
        UserRole.Role userRole = authenticatedUser.getRole();
        if (userRole == UserRole.Role.ADMIN) {
            chain.doFilter(request, response);
            return;
        }
        if (userRole == UserRole.Role.CLIENT && path.startsWith("/jsp/admin/")) {
            request.setAttribute("error", "У вас недостаточно прав!");
            request.getRequestDispatcher("/jsp/exception/error.jsp").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}