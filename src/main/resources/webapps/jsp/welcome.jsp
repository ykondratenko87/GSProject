<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="text/html">
    <title>Welcome </title>
    <link rel="stylesheet" href="../css/html.css">
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/login">
        <h3 class="text-center">Welcome</h3>
        <p class="text-white-50 mb-5">Please select one of the option!</p>

        <p>Have an account? <a href="../GSProject/jsp/login.jsp" class="text-white-50 fw-bold">Log in</a>.</p>
        <p>Don't have an account? <a href="../GSProject/jsp/registration.jsp" class="text-white-50 fw-bold">Sign Up</a>.</p>
    </form>
</div>
</body>
</html>