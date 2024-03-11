<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.tms.gsproject.entity.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="text/html">
    <title>Error Page</title>
    <style>
        html {
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

        .error-message {
            margin-top: 20px;
            color: red;
        }
    </style>
</head>
<body>
<h2 class="eror">${error}</h2>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/error">
        <h3 class="text-center">Error</h3>
        <div class="error-message">
            <p>Sorry, something went wrong.</p>
            <p>Please try again later.</p>
        </div>
    </form>
</div>
</body>
</html>