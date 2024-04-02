<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<html>
<head>
    <title>Корзина товаров</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            background-color: #214c84;
            background-blend-mode: overlay;
            display: flex;
            align-items: center;
            justify-content: center;
            background-repeat: no-repeat;
            background-size: cover;
            height: 100%;
        }

        body {
            background-color: transparent;
        }

        .registration-cssave form {
            max-width: 800px;
            padding: 50px 70px;
            border-radius: 10px;
            box-shadow: 4px 4px 15px rgba(0, 0, 0, 0.2);
            background-color: #fff;
        }

        .registration-cssave form h3 {
            font-weight: bold;
            margin-bottom: 30px;
        }

        .registration-cssave form label {
            display: flex;
            flex-direction: column;
            margin-bottom: 25px;
        }

        .registration-cssave form label > span {
            margin-bottom: 5px;
        }

        @media (max-width: 576px) {
            .registration-cssave form {
                padding: 50px 20px;
            }
        }

        .product-card h4 {
            margin: 0;
        }

        .product-card p {
            margin: 5px 0;
            font-size: 14px;
            color: #555;
        }

        .text-center::before {
            content: none;
        }

        .registration-cssave .item {
            border-radius: 10px;
            padding: 10px 20px;
            width: 100%;
            box-sizing: border-box;
        }

        @media (max-width: 576px) {
            .registration-cssave form {
                padding: 50px 20px;
            }
        }
    </style>
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/account">
        <div>
            <h2>Аккаунт пользователя</h2>
            <div class="user-card">
                <c:if test="${not empty user}">
                    <h3>User Details</h3>
                    <p>ID: ${user.id}</p>
                    <p>Name: ${user.name}</p>
                    <p>Surname: ${user.surname}</p>
                    <p>Login: ${user.login}</p>
                    <p>Password: ${user.password}</p>
                </c:if>
                <c:if test="${not empty searchResult}">
                    <p>${searchResult}</p>
                </c:if>
            </div>
        </div>
        <h3 class="text-center">Registration</h3>
        <div class="form-group">
            <label>
                <span>Name</span>
                <input class="form-control item" type="text" name="name" placeholder="Name" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <span>Surname</span>
                <input class="form-control item" type="text" name="surname" placeholder="Surname" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <span>Login</span>
                <input class="form-control item" type="text" name="login" maxlength="15" minlength="4"
                       pattern="^[a-zA-Z0-9_.-]*$" placeholder="Login" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <span>Password</span>
                <input class="form-control item" type="password" name="password" minlength="6" placeholder="Password"
                       required>
            </label>
        </div>
        <div class="form-group">
            <button type="submit" name="updatedates">Update</button>
        </div>
    </form>
</div>
</body>
</html>