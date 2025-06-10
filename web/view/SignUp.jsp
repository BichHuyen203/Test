<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Clubs" %>
<%@page import="dao.ClubDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="css/signup.css">
    </head>
    <body>
        <div class="shadow"></div>
        <div class="container">
            <%ArrayList<Clubs> club = ClubDAO.GetNameClub();%>

            <div class="signup-title">
                <h2>Sign Up</h2>
            </div>

            <form action="Login" method="POST" class="signup-form">

                <input type="hidden" name="action" value="Signup">

                <div class="form-group">
                    <label for="username" class="form-label">UserName:</label>
                    <input type="text" name="usernamesignup" id="username" class="form-input" placeholder="Enter your username">
                </div>

                <div class="form-group">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" name="passwordsignup" id="password" class="form-input" placeholder="Enter your password">
                </div>

                <div class="form-group">
                    <label for="confirm-password" class="form-label">Confirm Password:</label>
                    <input type="password" name="psa" id="confirm-password" class="form-input" placeholder="Confirm your password">
                </div>

                <div class="form-group">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" id="email" class="form-input" placeholder="example@domain.com" required>
                </div>

                <div class="form-group">
                    <label for="club" class="form-label">Club:</label>
                    <select name="club" id="club" class="form-select">
                        <% for (Clubs role : club) { %>
                        <option value="<%= role.getClubID() %>"><%= role.getClubName() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-submit-group">
                    <button type="submit" class="form-submit">Sign Up</button>
                </div>

                <div class="error-messages">
                    <% String check = (String) request.getAttribute("check"); %>
                    <% if (check != null) { %>
                    <p class="error-message"><i><%= check %></i></p>
                            <% } %>

                    <% String email = (String) request.getAttribute("email"); %>
                    <% if (email != null) { %>
                    <p class="error-message"><i><%= email %></i></p>
                            <% } %>

                    <% String pass = (String) request.getAttribute("pass"); %>
                    <% if (pass != null) { %>
                    <p class="error-message"><i><%= pass %></i></p>
                            <% } %>

                    <% String passError = (String) request.getAttribute("passError"); %>
                    <% if (passError != null) { %>
                    <p class="error-message"><i><%= passError %></i></p>
                            <% } %>

                    <% String emailExistsMessage = (String) request.getAttribute("emailExistsMessage"); %>
                    <% if (emailExistsMessage != null) { %>
                    <p class="error-message"><i><%= emailExistsMessage %></i></p>
                            <% } %>
                </div>
                <div class="form-back-group">
                    <a href="javascript:void(0);" class="form-back" onclick="history.back()">Trở về trang đăng nhập</a>
                </div>
            </form>
        </div>
    </body>
</html>