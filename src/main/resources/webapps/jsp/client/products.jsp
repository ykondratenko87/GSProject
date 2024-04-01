<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders</title>
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

        .product-card {
            width: 200px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin: 10px;
            display: inline-block;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .product-card h4 {
            margin: 0;
            font-size: 16px;
        }

        .product-card p {
            margin: 5px 0;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
<div>
    <form method="post" action="http://localhost:8080/GSProject/products">

        <div>
            <%--@declare id="productid"--%><%--@declare id="count"--%><h2>Товары</h2>
            <ul>
                <c:if test="${not empty products}">
                    <div class="all-products">
                        <h3>All Products</h3>
                        <div class="product-grid">
                            <c:forEach var="product" items="${products}">
                                <div class="product-card">
                                    <h4>ID: ${product.id}</h4>
                                    <h4>${product.name}</h4>
                                    <p>Price: ${product.price}</p>
                                    <p>Quantity: ${product.quantity}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </ul>
            <button type="submit" class="show-products" name="showproducts">Show All Products</button>
            <h2>Добавить в корзину</h2>
            <label for="productId">ID товара:</label><br>
            <input type="text" name="idProduct" placeholder="id"><br>
            <label for="count">Количество:</label><br>
            <input type="text" name="ProductCount" placeholder="count"><br><br>
            <button type="submit" name="addProductByBasket">Добавить в корзину</button>
        </div>
        <div class="container">
            <button type="submit" name="basket" formaction="http://localhost:8080/GSProject/basket" formmethod="post"
                    class="btn btn-primary btn-cart">Корзина
            </button>
        </div>
    </form>
</div>
</body>
</html>