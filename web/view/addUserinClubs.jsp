<%-- 
    Document   : addUserinClubs
    Created on : Mar 19, 2025, 6:18:58 PM
    Author     : P15Gen1
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.User" %>
<%@page import="dao.UsersDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"  href="css/addinclub.css">
    </head>
    <body>
        <% ArrayList<User> list = UsersDAO.getUsersBySignUpStatusPending(); %>
        <div class="content-wrapper">
            <% if (list != null && !list.isEmpty()) { %>
            <table id="userPendingTable" class="styled-table">
                <thead>
                    <tr>
                        <th class="table-header">Full Name</th>
                        <th class="table-header">Club Name</th>
                        <th class="table-header">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (User u : list) { %>
                    <tr class="user-row">
                        <td class="user-name">
                            <i class="bi bi-person"></i> <%= u.getFullName() %>
                        </td>
                        <td class="club-name">
                            <i class="bi bi-people"></i> <%= u.getClubName() %>
                        </td>
                        <td class="status-actions">
                            <form action="addUserInClub" method="POST" class="status-form">
                                <input type="hidden" name="userID" value="<%= u.getUserID() %>">
                                <button type="submit" name="status" value="ACCEPTED" class="action-btn accept-btn">
                                    <i class="bi bi-check-circle-fill"></i> Accept
                                </button>
                                <button type="submit" name="status" value="REJECTED" class="action-btn reject-btn">
                                    <i class="bi bi-x-circle-fill"></i> Reject
                                </button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p class="no-request">Không có tài khoản nào đang chờ duyệt.</p>
            <% } %>
        </div>
    </body>
</html>
