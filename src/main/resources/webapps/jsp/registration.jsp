<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="text/html">
    <title>Форма регистрации</title>
    <link rel="stylesheet" href="../css/html.css">
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/registration">
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
            <button class="btn btn-primary btn-block create-account" type="Submit">Sign up</button>
        </div>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
    </form>
</div>
</body>
</html>