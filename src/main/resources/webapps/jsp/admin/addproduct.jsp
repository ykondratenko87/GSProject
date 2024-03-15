<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
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

        .registration-cssave {
            padding: 50px 0;
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

        .registration-cssave .item {
            border-radius: 10px;
            padding: 10px 20px;
            width: 100%;
            box-sizing: border-box;
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

        .text-center::before {
            content: none;
        }

        .registration-cssave .create-account:hover {
            background-color: #2a6dbb;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/addproduct">
        <h3>Add New Product</h3>
        <div>
            <label>
                <span>Name:</span>
                <input class="form-control item" type="text" name="name" placeholder="Name" required>
            </label>
        </div>
        <div>
            <label>
                <span>Type:</span>
            </label>
            <select class="form-control item" type="text" name="type" placeholder="Type" required>
                <option value="STRATEGY">Strategy</option>
                <option value="SPORT">Sport</option>
                <option value="SHOOTER">Shooter</option>
                <option value="FIGHTING">Fighting</option>
                <option value="RACING">Racing</option>
            </select>
        </div>
        <div>
            <label>
                <span>Price:</span>
                <input class="form-control item" type="number" name="price" min="0" step="0.01" placeholder="Price"
                       required>
            </label>
        </div>
        <div>
            <label>
                <span>Quantity:</span>
                <input class="form-control item" type="number" name="quantity" min="0" placeholder="Quantity" required>
            </label>
        </div>
        <button class="btn btn-primary btn-block create-account" type="submit" value="addproduct">Add Product</button>
    </form>
    <c:if test="${not empty message}">
        <div class="success-message">${message}</div>
    </c:if>
</div>
</body>
</html>