<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Clubs" %> 
<%@page  import="java.util.ArrayList" %>
<%@page import= "dao.UsersDAO" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/updateUser.css">
    </head>
    <body>
    <div class="update-user-container">
        <form action="Login" method="POST">
            <input type="hidden" name="action" value="update">
            <h1>Đổi thông tin tài khoản</h1>

            <div class="form-group">
                <label for="usernameup">UserName</label>
                <input type="text" id="usernameup" name="usernameup" value="<c:out value="${us}"/>">
            </div>

            <div class="form-group">
                <label for="passwordup">Password</label>
                <input type="text" id="passwordup" name="passwordup" value="<c:out value="${pss}"/>">
            </div>

            <div class="form-group">
                <label for="emailup">Email</label>
                <input type="email" id="emailup" name="emailup" value="<c:out value="${es}"/>" placeholder="example@domain.com" required>
            </div>

            <input type="submit" name="name" value="Update">

            <% String update = (String) request.getAttribute("update"); %>
            <% if (update != null) { %>
                <p class="status-message"><i><%= update %></i></p>
            <% } %>
        </form>   
    </div>
</body>

</html>