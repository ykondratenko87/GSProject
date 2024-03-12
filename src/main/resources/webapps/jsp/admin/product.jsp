<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="text/html">
    <title>Product Management</title>
    <link rel="stylesheet" href="../../css/html.css">
</head>
<body>
<div class="registration-cssave">

    <form method="post" action="http://localhost:8080/ProjectShop/product">
        <h2>Product Management</h2>
        <div>
            <label>
                <span>Product ID:</span>
                <input class="form-control item" type="text" name="productId" placeholder="ProductId">
            </label>
        </div>
        <button class="btn btn-primary btn-block create-account" type="submit" name="action" value="search">Search
        </button>
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
                <option value="FIGHTING">Racing</option>
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
                <input class="form-control item" type="number" name="quantity" min="0" placeholder="Quantity"
                       required>
            </label>
        </div>
        <button class="btn btn-primary btn-block create-account" type="submit" name="action" value="add">Add
            Product
        </button>
        <h3>Delete Product</h3>
        <div>
            <label>
                <span>Product ID:</span>
                <input class="form-control item" type="text" name="deleteProductId" placeholder="DeleteProductId">
            </label>
        </div>
        <input type="hidden" id="deleteProductIdHidden" name="deleteProductId" value="${product.id}">
        <button class="btn btn-primary btn-block create-account" type="submit" name="action" value="delete">Delete
            Product
        </button>

    </form>
    <c:if test="${not empty product}">
        <h3>Product Details</h3>
        <p>ID: ${product.id}</p>
        <p>Name: ${product.name}</p>
        <p>Type: ${product.type}</p>
        <p>Price: ${product.price}</p>
        <p>Quantity: ${product.quantity}</p>
    </c:if>

    <c:if test="${not empty searchResult}">
        <p>${searchResult}</p>
    </c:if>
    <c:if test="${not empty message}">
        <div class="success-message">${message}</div>
    </c:if>
    <c:if test="${not empty deletionMessage}">
        <div class="success-message">${deletionMessage}</div>
    </c:if>
</div>
</body>
</html>