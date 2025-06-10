<%-- 
    Document   : eventPar
    Created on : Mar 19, 2025, 8:04:30 AM
    Author     : P15Gen1
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.EventParticipants" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/eventPar.css">
    </head>
    <body>
        <form action="EventP" method="POST" id="eventRequestForm">
            <input type="hidden" name="event" value="totalp">
            <% ArrayList<EventParticipants> eventList = (ArrayList<EventParticipants>) request.getAttribute("event"); %>
            <input type="submit" value="Xem sự kiện" id="viewEventButton">
        </form>

        <div id="eventTableContainer">
            <% if (eventList != null) { %>
            <div class="shadow-box"></div> 
            <table border="1" id="eventTable">
                <thead>
                    <tr id="eventTableHeader">
                        <th>Tên sự kiện</th>
                        <th>Trạng thái</th>
                        <th>Tên người tham gia</th>
                        <th>Số lượng tham gia</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (EventParticipants ev : eventList) { %>
                    <tr class="eventRow">
                        <td class="eventName"><%= ev.getEventName() %></td>
                        <td class="eventStatus"><%= ev.getStatus() %></td>
                        <td class="participantName"><%= ev.getParticipantName() %></td>
                        <td class="participantCount"><%= ev.getNumberOfParticipants() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>
        </div>

    </body>
</html>
