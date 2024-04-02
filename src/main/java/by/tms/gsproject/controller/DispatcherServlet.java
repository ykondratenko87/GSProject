package by.tms.gsproject.controller;

import by.tms.gsproject.controller.basket.*;
import by.tms.gsproject.controller.order.*;
import by.tms.gsproject.controller.product.*;
import by.tms.gsproject.controller.user.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

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
        if ("/products".equals(path) && request.getParameter("showproducts") != null) {
            ShowProductsClientController showProductsClientController = new ShowProductsClientController();
            showProductsClientController.showAProducts(request, response);
        }
        if ("/products".equals(path) && request.getParameter("addProductByBasket") != null) {
            BasketController basketController = new BasketController();
            basketController.addOrderByBasket(request, response);
        }
        if ("/basket".equals(path) && request.getParameter("basket") != null) {
            AllOrdersController allOrdersController = new AllOrdersController();
            try {
                allOrdersController.allOrders(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if ("/basket".equals(path) && request.getParameter("makeOrder") != null) {
            OrderController orderController = new OrderController();
            try {
                orderController.makeOrder(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if ("/basket".equals(path) && request.getParameter("cleanBasket") != null) {
            CleanBasketController cleanBasketController = new CleanBasketController();
            try {
                cleanBasketController.cleanBasket(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if ("/account".equals(path) && request.getParameter("account") != null) {
            EditAccountController editAccountController = new EditAccountController();
            try {
                editAccountController.showDates(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if ("/account".equals(path) && request.getParameter("updatedates") != null) {
            EditAccountController editAccountController = new EditAccountController();
            try {
                editAccountController.updateDates(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}