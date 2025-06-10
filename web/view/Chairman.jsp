<%-- 
    Document   : Chairman
    Created on : Mar 11, 2025, 9:30:23 PM
    Author     : P15Gen1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, model.EventRequest, dao.EventRequestDAO" %>
<%@page import="model.User" %>
<%@page import="model.EventParticipants" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/chairman.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <jsp:include page = "/view/header.jsp"/>
    <jsp:include page = "/view/topnav.jsp"/>

    <div id="notification" style="<c:if test='${empty message and empty deleteevent}'>display: none;</c:if>">
        <c:out value="${message}"/> <c:out value="${deleteevent}"/>
    </div>
    

    <div class="content-wrapper">
        <button class="tab-button" onclick="showTab('searchBox')">
            <i class="fas fa-search"></i> Tìm kiếm
        </button>
        <button class="tab-button" onclick="showTab('addEvent')">
            <i class="fas fa-plus"></i> Thêm sự kiện
        </button>
        <button class="tab-button" onclick="showTab('deleteEvent')">
            <i class="fas fa-trash"></i> Xóa sự kiện
        </button>
        <button class="tab-button" onclick="showTab('reports')">
            <i class="fas fa-chart-bar"></i> Báo cáo
        </button>
        <button class="tab-button" onclick="submitRequestForm()">
            <i class="fas fa-paper-plane"></i> Yêu cầu
        </button>
        <button class="tab-button" onclick="showTab('sendingEmail')">
            <i class="fas fa-envelope"></i> Gửi Email
        </button>
    </div>

    <form id="mainRequestForm" action="processRequest" method="post" style="display: none;">
        <input type="hidden" name="name">
    </form>

    <div class="tab-content" id="searchBox">
        <form id="searchForm" action="Login" method="POST">
            <input type="hidden" name="action" value="search">
            <label for="searchInput">Tìm kiếm thành viên:</label>
            <input type="text" id="searchInput" name="searchTerm" placeholder="Nhập tên, mã số hoặc vai trò" required />
            <button type="submit" id="searchButton">
                <i class="fas fa-search"></i> Tìm kiếm
            </button>
        </form>
    </div>

    <div class="tab-content" id="addEvent">
        <jsp:include page="/view/addEvent.jsp"/>
    </div>

    <div class="tab-content" id="deleteEvent">
        <jsp:include page="/view/DeleteEvent.jsp"/>
    </div>

    <div class="tab-content" id="reports">
        <jsp:include page="/view/viewReports.jsp"/>
    </div>
    <div class="tab-content" id="sendingEmail">
        <jsp:include page="/view/sendingEmail.jsp"/>
    </div>

    <div class="tab-content" id="requests">
        <c:out value="${deleteevent}"/>
        <form id="mainRequestForm" action="processRequest" method="post">
            <button type="submit" id="submitMainRequest" name="name">
                <i class="fas fa-paper-plane"></i> Gửi yêu cầu
            </button>
        </form>
    </div>

    <%
    ArrayList<User> searchResults = (ArrayList<User>) request.getAttribute("arr");
    String noResultsMessage = (String) request.getAttribute("i");
    if (searchResults != null || noResultsMessage != null) {
    %>



    <div id="searchModal" class="modalchairman">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <table border="1">
                <% if (noResultsMessage != null) { %>
                <p><strong><%= noResultsMessage %></strong></p>
                <% } else if (searchResults != null && !searchResults.isEmpty()) { %>
                <tr>
                    <th>UserID</th>
                    <th>FullName</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Update</th>
                </tr>
                <% for (User user : searchResults) { %>
                <tr>
                    <td><%= user.getUserID() %></td>
                    <td><%= user.getFullName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <form action="update" method="POST">
                            <input type="hidden" name="userId" value="<%= user.getUserID() %>" />
                            <input type="hidden" name="clubId" value="<%= user.getClubID() %>" />
                            <select name="role">
                                <option value="Vicechairman">Vicechairman</option>
                                <option value="TeamLeader">TeamLeader</option>
                                <option value="Member">Member</option>
                            </select>
                            <input type="submit" name="update" class="update-btn" value="Cập nhật" />
                        </form>
                    </td>
                </tr>
                <% } %>
                <% } %>
            </table>
        </div>
    </div>
    <% } %>


    <c:if test="${not empty eventRequests}">

        <div id="requestModal" class="modalchairman">

            <div class="modal-content">
                <span class="close" onclick="closeRequestModal()">&times;</span>
                <h3>Danh sách yêu cầu</h3>

                <table id="requestTable" border="1">
                    <thead>
                        <tr>
                            <th>Event ID</th>
                            <th>User Name</th>
                            <th>Status</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="request" items="${eventRequests}">
                            <tr>
                                <td>${request.getEventID()}</td>
                                <td>${request.getFullName()}</td>
                                <td>${request.getStatus()}</td>
                                <td>
                                    <form action="processRequest" method="post">
                                        <input type="hidden" name="requestID" value="${request.getRequestID()}" />
                                        <input type="hidden" name="eventID" value="${request.getEventID()}" />
                                        <input type="hidden" name="userID" value="${request.getUserID()}" />
                                        <input type="submit" class="accept-btn" name="action" value="Accept" />
                                        <input type="submit" class="reject-btn" name="action" value="Reject" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
    <jsp:include page = "/view/chat.jsp"/>
    <jsp:include page = "/view/footer.jsp"/>


    <script>

        function submitRequestForm() {
            document.getElementById("mainRequestForm").submit();
        }
        function showTab(tabId) {
            document.querySelectorAll(".tab-content").forEach(content => {
                content.style.display = "none";
            });

            // Hiển thị nội dung được chọn
            document.getElementById(tabId).style.display = "block";
        }
        function openModal() {
            document.getElementById('searchModal').style.display = 'flex';
        }


        function closeModal() {
            document.getElementById('searchModal').style.display = 'none';
        }


        document.querySelector('.modal-content').addEventListener('click', function (event) {
            event.stopPropagation();
        });

        document.getElementById("openRequestModal").addEventListener("click", function () {
            document.getElementById("requestModal").style.display = "flex";
        });

        function closeRequestModal() {
            document.getElementById("requestModal").style.display = "none";
        }
        function showTab(tabId) {
            document.querySelectorAll(".tab-content").forEach(content => {
                content.style.display = "none";
            });

           
            document.getElementById(tabId).style.display = "block";
        }
    </script>
</html>
