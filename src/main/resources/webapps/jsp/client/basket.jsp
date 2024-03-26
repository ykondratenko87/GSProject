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

        .product-cards {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .product-card {
            width: 200px;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }

        .product-card h4 {
            margin: 0;
        }

        .product-card p {
            margin: 5px 0;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/basket">
        <div>
            <h2>Корзина товаров</h2>
            <div class="product-cards">
                <c:forEach var="product" items="${orders.products}">
                    <div class="product-card">
                        <h4>${product.name}</h4>
                        <p>${product.type}</p>
                        <p>Цена: ${product.price}</p>
                    </div>
                </c:forEach>
            </div>
            <div>
                <h2>Статус заказа:</h2>
                <p>${orderStatus}</p>
            </div>
            <button type="submit" name="makeOrder">Оформить</button>
            <button type="submit" name="cleanBasket">Удалить все</button>
        </div>
    </form>
</div>
</body>
</html>