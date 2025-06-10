<%@page import="java.util.ArrayList" %>
<%@page import="model.User" %>
<%@page import="model.EventParticipants" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Event Participants</title>
        <link rel="stylesheet" href="css/member.css">
        <script>
            function toggleDiv(divId) {
                var div = document.getElementById(divId);
                if (div.style.display === "none") {
                    div.style.display = "block";
                } else {
                    div.style.display = "none";
                }
            }
        </script>
    </head>

    <body id="memberbody">
        <jsp:include page="/view/header.jsp"/>
        <jsp:include page="/view/topnav.jsp"/>

        <div class="event-participants-container">
            <div class="button-container">
                <button class="toggle-btn" onclick="toggleDiv('formDivSection')">Mở Form</button>
            </div>

            <div id="notificationInfoDiv" class="toggle-content">
                <% String eventcheck = (String) request.getAttribute("eventcheck"); %>
                <% if(eventcheck != null) { %>
                <p class="error-message"><%= eventcheck %></p>
                <% } %>
            </div>

            <div id="formDivSection" class="toggle-content">
                <jsp:include page="/view/EventCheck.jsp"/>
            </div>

            <% ArrayList<EventParticipants> hismem = (ArrayList<EventParticipants>) request.getAttribute("hismem"); %>
            <% if (hismem != null) { %>
            <table class="event-table" border="1" cellpadding="10" cellspacing="0">
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Event Name</th>
                        <th>Event Date</th>
                        <th>Location</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (EventParticipants evv : hismem) { %>
                    <tr>
                        <td><%= evv.getFullName() %></td>
                        <td><%= evv.getEventName() %></td>
                        <td><%= evv.getEventDate() %></td>
                        <td><%= evv.getLocation() %></td>
                        <td><%= evv.getStatus() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } %>

            <div class="re-event-section"><jsp:include page="/view/ReEvent.jsp"/></div>
            <div class="update-user-section"><jsp:include page="/view/updateUser.jsp"/></div>
        </div>

        <jsp:include page="/view/footer.jsp"/>
        <jsp:include page="/view/chat.jsp"/>
    </body>
</html>
