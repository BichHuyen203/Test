<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Events" %>
<%@page import="dao.EventDAO" %>
<%@page import="dao.EventRequestDAO" %>
<%@page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Events Page</title>
        <link rel="stylesheet" type="text/css" href="css/ReEvent1.css">
    </head>
    <body>
        <div class="container">
            <% 
                ArrayList<User> user = (ArrayList<User>)(session.getAttribute("user")); 
                ArrayList<Events> event = EventDAO.GetAllEvent();
                if (event != null) {
                    for (Events u : event) { 
            %>
            <div class="event-box">
                <strong class="event-title">Sự Kiện:</strong> <%= u.getEventName() %><br>
                <strong class="event-description">Mô tả:</strong> <%= u.getDescription() %><br>
                <strong class="event-date">Thời gian tổ chức:</strong> <%= u.getEventDate() %><br>
                <strong class="event-location">Địa điểm:</strong> <%= u.getLocation() %><br>

                <%
                    String requestStatus = EventRequestDAO.getUserEventRequestStatus(user.get(0).getUserID(), u.getEventID());
                    if (requestStatus == null || requestStatus.equals("Rejected")) {
                %>
                <form id="registerForm_<%= u.getEventID() %>" onsubmit="return registerEvent(<%= u.getEventID() %>, event)">
                    <input type="hidden" name="eventID" value="<%= u.getEventID() %>">
                    <input type="submit" value="Đăng Kí" class="register-button" id="submit_<%= u.getEventID() %>">
                </form>
                <% } else if (requestStatus.equals("Pending")) { %>
                <span id="status_<%= u.getEventID() %>">Đang Trong quá trình xử lý</span>
                <% } else if (requestStatus.equals("Accepted")) { %>
                <span id="status_<%= u.getEventID() %>">Sự kiện này bạn đã tham gia rồi!</span>
                <% } %>
            </div>
            <% 
                    }
                } 
            %>
        </div>
        <script>
            function registerEvent(eventID, event) {
                event.preventDefault();  // Ngăn form gửi yêu cầu truyền thống

                var form = document.getElementById("registerForm_" + eventID);
                var submitButton = document.getElementById("submit_" + eventID);
                var statusText = document.getElementById("status_" + eventID);

               
                submitButton.style.display = 'none';

                
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "reevent", true);  // Thay "reevent" bằng đường dẫn servlet của bạn
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                       
                        statusText.innerHTML = "Đang Trong quá trình xử lý";
                        statusText.style.display = 'block';
                    }
                };

                
                var formData = new FormData(form);
                xhr.send(new URLSearchParams(formData).toString());

                return false;  
            }
        </script>
    </body>
</html>