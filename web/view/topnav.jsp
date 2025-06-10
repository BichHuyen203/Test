<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Account Information</title>
        <link rel="stylesheet" href="css/topnav.css">
   
    </head>
    <body>
        <h2>Thông tin tài khoản</h2>
        <div id="userInfo">
            <c:forEach var="user" items="${user}">
                <table>
                    <tr>
                        <th>UserID:</th>
                        <td><c:out value="${user.userID}" /></td>
                    </tr>
                    <tr>
                        <th>FullName:</th>
                        <td><c:out value="${user.fullName}" /></td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td><c:out value="${user.email}" /></td>
                    </tr>
                    <tr>
                        <th>Role:</th>
                        <td><c:out value="${user.role}" /></td>
                    </tr>
                </table>
            </c:forEach>
        </div>
    </body>
</html>
