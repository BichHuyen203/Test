<%-- 
    Document   : index
    Created on : Feb 26, 2025, 2:15:36 PM
    Author     : P15Gen1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% response.sendRedirect(request.getContextPath() + "/Login"); %>
    </body>
</html>
