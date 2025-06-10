<%-- 
    Document   : teamleader
    Created on : Mar 9, 2025, 9:20:28 PM
    Author     : P15Gen1
--%>
<%@ page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        #reportMessage {
            position: fixed;
            top: 20px; 
            left: 50%;
            transform: translateX(-50%);
            background-color: #28a745; 
            color: white;
            padding: 15px 30px;
            border-radius: 8px;
            z-index: 9999;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            font-size: 16px;
            font-weight: bold;
            display: none; 
        }

    </style>
    <body>

        <jsp:include page = "/view/header.jsp"/>

        <jsp:include page = "/view/topnav.jsp"/>
        <jsp:include page = "/view/chat.jsp"/>
        <jsp:include page = "/view/addReports.jsp"/>
        <jsp:include page = "/view/eventPar.jsp"/>
        <jsp:include page = "/view/footer.jsp"/>
        <jsp:include page = "/view/chat.jsp"/>
        <div id="reportMessage">
            <c:out value="${addreport}"/>
        </div>
    </body>
    <script>
        window.onload = function () {
            var reportMessage = document.getElementById("reportMessage");

           
            if (reportMessage.textContent.trim() !== "") {
                reportMessage.style.display = "block"; 
            }
        };

    </script>
</html>
