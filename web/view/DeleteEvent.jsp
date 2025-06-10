<%-- 
    Document   : DeleteEvent
    Created on : Mar 16, 2025, 10:07:08 AM
    Author     : P15Gen1
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Events" %>
<%@ page import="dao.EventDAO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Event</title>
        <link rel="stylesheet" href="css/deleteevent.css">
    </head>
    <body>

        <div class="delete-container">
            <div class="delete-shadow"></div>
            <div class="delete-form-wrapper">
                <h2><i class="bi bi-trash3-fill"></i> Xóa Sự Kiện</h2>
                <form action="DeleteEvent" method="POST">
                    <% 
                        ArrayList<Events> e = EventDAO.GetAllEvent(); 
                        if (e == null || e.isEmpty()) { 
                    %>
                        <p style="color: red;">Không có sự kiện nào để hiển thị!</p>
                    <% } else { %>
                        <div class="delete-group">
                            <label for="deleteEvent">Chọn sự kiện:</label>
                            <select name="event" id="deleteEvent" class="delete-select">
                                <% for (Events role : e) { %>
                                <option value="<%= role.getEventID() %>"><%= role.getEventName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <button type="submit" class="delete-submit"><i class="bi bi-trash"></i> Xóa</button>
                    <% } %>
                </form>
            </div>
        </div>

    </body>
</html>