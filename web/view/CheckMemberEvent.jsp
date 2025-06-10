<%-- 
    Document   : CheckMemberEvent
    Created on : Mar 15, 2025, 4:23:56 PM
    Author     : P15Gen1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="model.User" %>
<%@ page import="model.Image" %>
<%@ page import="dao.ImageDAO" %>
<%@ page import="model.Events" %>
<%@ page import="dao.EventDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/checkmemberE.css">
    </head>

    <body>
        <div id="uniqueContainer" class="imageTableContainer">
            <table border="1" class="imageTable">
                <% 
                    ArrayList<Image> img = ImageDAO.GetAllMemberWithAcccept(); 
                    if (img != null && !img.isEmpty()) {
                %>
                <tr class="tableHeader">
                    <td>Full Name</td>
                    <td>Image Name</td>
                    <td>Event ID</td>
                    <td>Description</td>
                    <td>View</td>
                    <td>Action</td>
                </tr>

                <% 
                    for (Image i : img) {
                %>
                <tr class="tableRow">
                    <td><%= i.getFullname() %></td>
                    <td><%= i.getImageName() %></td>
                    <td><%= i.getEventID() %></td>
                    <td><%= i.getDescription() %></td>

                    <td>
                        <form action="viewImage" method="GET">
                            <input type="hidden" name="id" value="<%= i.getId() %>" />
                            <input type="submit" value="View" />
                        </form>
                    </td>
                    <td>
                        <form action="accept" method="Post">
                            <input type="hidden" name="userId" value="<%= i.getUserId() %>" />
                            <input type="hidden" name="eventId" value="<%= i.getEventID() %>" />
                            <input type="submit" name="status" value="Attended" />
                            <input type="submit" name="status" value="Absent" />
                        </form>
                    </td>
                </tr>
                <% 
                    }
                %>
                <% 
                    } 
                %>
            </table>
        </div>
    </body>
</html>