<%-- 
    Document   : Vicechairman
    Created on : Mar 11, 2025, 9:30:41 PM
    Author     : P15Gen1
--%>
<%@page import="model.Image" %>
<%@page import="dao.ImageDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="model.Clubs" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/vicechairman.css">
        
    </head>
    <body>
        <jsp:include page = "/view/chat.jsp"/>
        <jsp:include page = "/view/header.jsp"/>
        <jsp:include page = "/view/topnav.jsp"/>
        
        <jsp:include page = "/view/CheckMemberEvent.jsp"/>
        <h6> Tạo Club :</h6>
        <jsp:include page = "/view/addCLub.jsp"/>
        <c:if test="${not empty checkevent1}">
            <div id="eventMessage" class="alert-box">
                <i class="bi bi-exclamation-circle"></i> 
                <c:out value="${checkevent1}"/>
            </div>
        </c:if>

        <jsp:include page = "/view/addUserinClubs.jsp"/>
        <jsp:include page = "/view/footer.jsp"/>
    </body>
</html>
