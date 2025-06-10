<%-- 
    Document   : admin
    Created on : Feb 24, 2025, 6:45:58 PM
    Author     : P15Gen1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/admin.css">
    </head>
    <body>
        <jsp:include page = "header.jsp"/>
        <jsp:include page = "topnav.jsp"/>
        <jsp:include page = "/view/chat.jsp"/>


        <form action="delete" method="GET">
            <input type="submit" value="Hiển Thị Danh Sách">
            <% ArrayList<User> user = (ArrayList<User>) request.getAttribute("member"); %>
        </form>
        <% if (user != null) { %>
        <table border="1">
            <tr>
                <td>UserID</td>
                <td>FullName</td>
                <td>Email</td>
                <td>Role</td>
                <td>ClubID</td>
                <td>Status</td>
            </tr>
            <% for (User u : user) { %>
            <tr>
                <td><%= u.getUserID() %></td>
                <td><%= u.getFullName() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getRole() %></td>
                <td><%= u.getClubID() %></td>
                <td>
                    <form action="delete" method="POST" style="display:inline;" onsubmit="return confirmDelete(this);">
                        <input type="hidden" name="userID" value="<%= u.getUserID() %>">
                        <input type="submit" value="Xóa">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <% } %>

        <script>
            function confirmDelete(form) {
                const result = confirm("Bạn có chắc chắn muốn xóa tài khoản này không?");
                if (result) {
                    alert("Đã xóa thành công!");
                    return true;
                } else {
                    alert("Đã hủy!");
                    return false;
                }
            }
        </script>
        <jsp:include page = "/view/footer.jsp"/>
    </body>
</html>
