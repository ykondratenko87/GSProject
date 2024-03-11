package by.tms.gsproject.controller;

import by.tms.gsproject.controller.user.AuthenticationController;
import by.tms.gsproject.controller.user.RegistrationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/registration".equals(path)) {
            RegistrationController registrationController = new RegistrationController();
            registrationController.registration(request, response);
        }
        if ("/login".equals(path)) {
            AuthenticationController authenticationController = new AuthenticationController();
            authenticationController.authentication(request, response);
        }
    }
}