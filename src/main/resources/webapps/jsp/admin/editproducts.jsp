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

        .registration-cssave form button.create-account {
            width: 200px;
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
    <h3>Admin Panel: Products</h3>
    <form method="get" action="searchproduct.jsp">
        <div>
            <button type="submit" class="create-account">Search Product</button>
        </div>
    </form>
    <form method="get" action="deleteproduct.jsp">
        <div>
            <button type="submit" class="create-account">Delete Product</button>
        </div>
    </form>
    <form method="get" action="addproduct.jsp">
        <div>
            <button type="submit" class="create-account">Add New Product</button>
        </div>
    </form>
    <form method="post" action="http://localhost:8080/GSProject/editproducts">
        <div>
            <button type="submit" class="create-account">Show All Products</button>
        </div>
    </form>
    <c:if test="${not empty products}">
        <div class="all-products">
            <h3>All Products</h3>
            <ul>
                <c:forEach var="product" items="${products}">
                    <li>ID: ${product.id}, Name: ${product.name}, Type: ${product.type}, Price: ${product.price},
                        Quantity: ${product.quantity}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</div>
</body>
</html>