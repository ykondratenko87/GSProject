package by.tms.gsproject.controller;

import by.tms.gsproject.controller.basket.BasketController;
import by.tms.gsproject.controller.basket.CleanBasketController;
import by.tms.gsproject.controller.order.AllOrdersController;
import by.tms.gsproject.controller.order.OrderController;
import by.tms.gsproject.controller.product.*;
import by.tms.gsproject.controller.user.*;
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

        if ("/editproducts".equals(path)) {
            ShowAllProductsController showAllProductsController = new ShowAllProductsController();
            showAllProductsController.showAllProducts(request, response);
        }
        if ("/editusers".equals(path)) {
            ShowAllUsersController showAllUsersController = new ShowAllUsersController();
            showAllUsersController.showAllUsers(request, response);
        }
        if ("/deleteuser".equals(path)) {
            DeleteUserController deleteUserController = new DeleteUserController();
            deleteUserController.deleteUser(request, response);
        }
        if ("/searchuser".equals(path)) {
            SearchUserController searchUserController = new SearchUserController();
            searchUserController.searchUser(request, response);
        }
        if ("/admin".equals(path)) {
            LogoutAdminController logoutAdminController = new LogoutAdminController();
            logoutAdminController.logoutAdmin(request, response);
        }
        if ("/client".equals(path)) {
            LogoutClientController logoutClientController = new LogoutClientController();
            logoutClientController.logoutClient(request, response);
        }
        if ("/products".equals(path)) {
            ShowProductsClientController showProductsClientController = new ShowProductsClientController();
            showProductsClientController.showAProducts(request, response);
        }
        if (request.getParameter("addtobasket") != null) {
            BasketController basketController = new BasketController();
            basketController.addOrderByBasket(request, response);
        }
        if (request.getParameter("makeOrder") != null) {
            OrderController orderController = new OrderController();
            orderController.makeOrder(request, response);
        }
        if (request.getParameter("gotobasket") != null) {
            AllOrdersController allOrdersController = new AllOrdersController();
            allOrdersController.allOrders(request, response);
        }
        if (request.getParameter("cleanBasket") != null) {
            CleanBasketController cleanBasketController = new CleanBasketController();
            cleanBasketController.cleanBasket(request, response);
        }
    }
}