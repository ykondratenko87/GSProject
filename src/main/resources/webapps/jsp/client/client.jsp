<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client panel</title>
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

        .registration-cssave form button.create-account {
            width: 200px;
        }

        body {
            background-color: transparent;
        }

        .registration-cssave {
            padding: 50px 0;
        }

        #logout-form .logout-button {
            background-color: red;
        }

        #logout-form .logout-button:hover {
            background-color: darkred;
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

        .registration-cssave .create-account {
            border-radius: 30px;
            padding: 10px 20px;
            font-size: 18px;
            font-weight: bold;
            background-color: #3f93ff;
            border: none;
            color: white;
            margin-top: 20px;
        }

        @media (max-width: 576px) {
            .registration-cssave form {
                padding: 50px 20px;
            }
        }

        .registration-cssave .create-account:hover {
            background-color: #2a6dbb;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="registration-cssave">
    <h3>Your Account</h3>
    <form method="post" action="jsp/client/products.jsp">
        <div>
            <button type="submit" class="create-account">Products</button>
        </div>
    </form>
    <form>
        <div class="container">
            <button type="submit" name="account" formaction="http://localhost:8080/GSProject/account" formmethod="post">
                Edit Account
            </button>
        </div>
    </form>
    <form id="logout-form" method="post" action="http://localhost:8080/GSProject/client">
        <div>
            <button type="submit" class="create-account logout-button">Log out</button>
        </div>
    </form>
</div>
</body>
</html>