<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="by.teachmeskills.projectshop.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="../css/html.css">
</head>
<body>
<div class="registration-cssave">
    <h2>User Management</h2>

    <form action="/ProjectShop/user" method="post">
        <div>
            <label for="userId">User ID:</label>
            <input type="text" id="userId" name="userId">
            <button type="submit" name="action" value="search">Search</button>
        </div>
    </form>

    <form action="/ProjectShop/user" method="post">
        <h3>Edit User</h3>
        <div>
            <label for="editUserId">User ID:</label>
            <input type="text" id="editUserId" name="editUserId" required>
            <button type="submit" name="action" value="load">Load User</button>
        </div>

        <form action="/ProjectShop/user" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="editUserId" value="${userToEdit.id}">
            <div>
                <label for="editName">Name:</label>
                <input type="text" id="editName" name="editName" value="${userToEdit.name}">
            </div>
            <div>
                <label for="editSurname">Surname:</label>
                <input type="text" id="editSurname" name="editSurname" value="${userToEdit.surname}">
            </div>
            <div>
                <label for="editLogin">Login:</label>
                <input type="text" id="editLogin" name="editLogin" value="${userToEdit.login}">
            </div>
            <div>
                <label for="editPassword">Password:</label>
                <input type="password" id="editPassword" name="editPassword" value="${userToEdit.password}">
            </div>
            <button type="submit">Save Changes</button>
        </form>
    </form>

    <form action="/ProjectShop/user" method="post">
        <h3>Delete User</h3>
        <div>
            <label for="deleteUserId">User ID:</label>
            <input type="text" id="deleteUserId" name="deleteUserId" required>
        </div>
        <button type="submit" name="action" value="delete">Delete User</button>
    </form>

    <form action="/ProjectShop/user" method="post">
        <input type="hidden" name="action" value="showAll">
        <button type="submit">Show all users</button>
    </form>

    <c:if test="${not empty users}">
        <h3>All Users</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${not empty user}">
        <h3>User Details</h3>
        <p>ID: ${user.id}</p>
        <p>Name: ${user.name}</p>
        <p>Surname: ${user.surname}</p>
        <p>Login: ${user.login}</p>
        <p>Password: ${user.password}</p>
        <p>Role: ${user.role}</p>
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