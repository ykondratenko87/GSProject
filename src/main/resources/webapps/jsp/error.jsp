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
    <link rel="stylesheet" href="../css/html.css">
</head>
<body>
<div class="registration-cssave">
    <form method="post" action="http://localhost:8080/GSProject/error">
        <h3 class="text-center">Error</h3>
        <div class="error-message">
            <p>Sorry, something went wrong.</p>
            <p>Please try again later.</p>
        </div>
    </form>
    >
</div>
</body>
</html>