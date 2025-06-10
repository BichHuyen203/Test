<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.User" %>
<%@page import="model.EventParticipants" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="EventP" method="GET">
            <input type="submit" value="Hiển Thị Danh Sách Sinh Viên Tham Gia Hoạt Động Của Câu Lạc Bộ">
        </form>
        
        <% 
            ArrayList<User> user = (ArrayList<User>) request.getAttribute("array");
            if (user != null) {
        %>
        <table border="1">
            <tr>
                <td>UserID</td>
                <td>FullName</td>
                <td>Email</td>
                <td>Role</td>
                <td>Status</td>
            </tr>
            <% for (User u : user) { %>
            <tr>
                <td><%= u.getUserID() %></td>
                <td><%= u.getFullName() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getRole() %></td>
                <td>
                    <form action="EventP" method="POST" style="display:inline;">
                        <input type="hidden" name="event" value="personp">
                        <input type="hidden" name="userID" value="<%= u.getUserID() %>">
                        <input type="submit" value="view">
                    </form>
                </td>
            </tr>
            <% } %> 
        </table>
        <% } %>
        
        <% 
            ArrayList<EventParticipants> eventP = (ArrayList<EventParticipants>) request.getAttribute("eventP");
            if (eventP != null) { 
        %>
        <table border="1">
            <tr>
                <td>FullName</td>
                <td>EventName</td>
                <td>EventDate</td>
                <td>Location</td>
                <td>Status</td>
            </tr>
            <% for (EventParticipants evv : eventP) { %>
            <tr>
                <td><%= evv.getFullName() %></td>
                <td><%= evv.getEventName() %></td>
                <td><%= evv.getEventDate() %></td>
                <td><%= evv.getLocation() %></td>
                <td><%= evv.getStatus() %></td>
            </tr>
            <% } %>
        </table>
        <% } %>
    </body>
</html>
