<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="text/html">
    <title>Форма входа</title>
    <link rel="stylesheet" href="../css/html.css">
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/jsp/error.jsp">
        <h3 class="text-center">Login</h3>
        <p class="text-white-50 mb-5">Please enter your login and password!</p>
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
            <button class="btn btn-primary btn-block create-account" type="Submit">Log in</button>
        </div>
        <p>Don't have an account? <a href="../jsp/registration.jsp" class="text-white-50 fw-bold">Sign Up</a>.</p>
    </form>
</div>
</body>
</html>