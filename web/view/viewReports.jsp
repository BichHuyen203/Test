<%-- 
    Document   : viewReports
    Created on : Mar 15, 2025, 9:06:04 PM
    Author     : P15Gen1
--%>
<%@ page import="java.util.ArrayList" %>
<%@page import="dao.ReportsDAO" %>
<%@page import="model.Reports" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="/Test/css/report.css">
    </head>
    <body>
        <%ArrayList <Reports> report = ReportsDAO.GetReports();%>
        <form action="#" method="post">
            <input type="button" value="Hiển thị bảng dữ liệu" onclick="showTable()">
        </form>

        <div id="tablereport" >
            <% if (report != null && !report.isEmpty()) { %>
            <table border="1">
                <tr>
                    <td>ClubID :</td>
                    <td>Semester :</td>
                    <td>EventSummary :</td>
                    <td>MemberChange :</td>
                    <td>ParticipationStats :</td>
                    <td>CreatedDate</td>
                    <td>Status</td>
                </tr>
                <% for (Reports u : report) { %>
                <tr>
                    <td><%= u.getClubID() %></td>
                    <td><%= u.getSemester() %></td>
                    <td><%= u.getEventSummary() %></td>
                    <td><%= u.getMemberChange() %></td>
                    <td><%= u.getParticipationStats() %></td>
                    <td><%= u.getCreatedDate() %></td>
                    <td>
                        <div id="deletetablereport"><form action="deleteReport" method="Post">
                                <input type="hidden" name="deletereport" value="<%= u.getReportID() %>">
                                <input type="submit" value="Delete" />
                            </form>
                        </div>

                    </td>
                </tr>
                <% } %>
            </table>
            <% } else { %>
            <p>No reports found.</p>
            <% } %>
        </div>

        <script>
            function showTable() {
                document.getElementById("reportTable").style.display = "block";
            }
        </script>
    </body>
</html>
