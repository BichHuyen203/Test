<%-- 
    Document   : addReports
    Created on : Mar 15, 2025, 9:05:00 PM
    Author     : P15Gen1
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="model.Clubs" %>
<%@page import="dao.ClubDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="/Test/css/addReport.css">
    </head>
    <body>
    <form action="addReport" method="GET" class="report-form">
        <% ArrayList<Clubs> club = ClubDAO.GetNameClub(); %>
        <table class="form-table">
            <tr> 
                <td>Club:</td>
                <td>
                    <select name="club" class="select-club">
                        <% for (Clubs role : club) { %>
                        <option value="<%= role.getClubID() %>"><%= role.getClubName() %></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td> 
                    <button type="submit" class="btn-fetch">
                        <i class="bi bi-search"></i> Get MemberChange And ParticipantStats
                    </button>
                </td>
            </tr>
        </table>
    </form>

    <form action="addReport" method="POST" class="report-form">
        <table class="form-table">
            <tbody> 
                <tr>
                    <td>MemberChange :</td>
                    <td>
                        <c:if test="${not empty memberchange}">
                            <input type="text" name="memberchange" value="${memberchange}" readonly class="readonly-input">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>EventSummary :</td>
                    <td><input type="text" name="eventSummary" maxlength="500" class="input-field"></td>
                </tr>
                <tr>
                    <td>ParticipationStats :</td>
                    <td>
                        <input type="text" name="ParticipantStats" value="${ParticipantStats}" class="input-field">
                    </td>
                </tr>
                <input type="hidden" name="clubID" value="${param.club}">
            </tbody>
        </table>
        <button type="submit" class="btn-create">
            <i class="bi bi-file-earmark-plus"></i> Tạo (Create new Reports)
        </button>
    </form>
</body>

</html>
