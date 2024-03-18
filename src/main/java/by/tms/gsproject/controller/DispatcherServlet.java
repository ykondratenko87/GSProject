package by.tms.gsproject.controller;

import by.tms.gsproject.controller.product.AddProductController;
import by.tms.gsproject.controller.product.DeleteProductController;
import by.tms.gsproject.controller.product.SearchProductController;
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
        if ("/addproduct".equals(path)) {
            AddProductController addProductController = new AddProductController();
            addProductController.addProduct(request, response);
        }
        if ("/deleteproduct".equals(path)) {
            DeleteProductController deleteProductController = new DeleteProductController();
            deleteProductController.deleteProduct(request, response);
        }
        if ("/searchproduct".equals(path)) {
            SearchProductController searchProductController = new SearchProductController();
            searchProductController.searchProduct(request, response);
        }
    }
}