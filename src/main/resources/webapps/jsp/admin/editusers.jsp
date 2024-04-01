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

        .user-cards {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .user-card {
            width: 300px;
            padding: 20px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .user-card p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<div class="registration-cssave">
    <h3>Admin Panel: Users</h3>
    <form method="post" action="http://localhost:8080/GSProject/jsp/admin/searchuser.jsp">
        <div>
            <button type="submit" class="create-account">Search User</button>
        </div>
    </form>
    <form method="post" action="http://localhost:8080/GSProject/jsp/admin/deleteuser.jsp">
        <div>
            <button type="submit" class="create-account">Delete User</button>
        </div>
    </form>
    <form method="post" action="http://localhost:8080/GSProject/editusers">
        <div>
            <button type="submit" class="create-account">Show All Users</button>
        </div>
    </form>
    <c:if test="${not empty users}">
        <div class="all-users">
            <h3>All Users</h3>
            <div class="user-cards">
                <c:forEach var="user" items="${users}">
                    <div class="user-card">
                        <p>ID: ${user.id}</p>
                        <p>Name: ${user.name}</p>
                        <p>Surname: ${user.surname}</p>
                        <p>Login: ${user.login}</p>
                        <p>Password: ${user.password}</p>
                        <p>Role: ${user.role}</p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>