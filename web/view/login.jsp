<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>

        <div class="shadow"></div>
        <div class="container">

            <% 
                String successMessage = (String) request.getAttribute("successMessage");
                if (successMessage != null) { 
            %>
            <div class="success-message">
                <%= successMessage %>
            </div>
            <% 
                } 
            %>

            <form action="Login" method="POST">
                <input type="hidden" name="action" value = "login">
                <div class="welcome">
                    <h3>Login</h3>
                    <p>Please enter your account</p>
                </div>
                <fieldset>
                    <div class="email-input">
                        <label for="email">Email</label>
                        <input type="text" name="usernamelogin" id="email" required placeholder="skibidi@gmail.com">
                    </div>

                    <% String error = (String) request.getAttribute("error"); %>
                    <% if (error != null) { %>
                    <tr>
                        <td>Error: </td>
                        <td><i><%= error %></i></td>
                    </tr>
                    <% } %>

                    <div class="password-input">
                        <label for="password">Password</label>
                        <input type="password" name="passwordlogin" id="password" required placeholder="sigma">
                    </div>
                </fieldset>

                <div class="save-data">
                    <div class="remember">
                        <input type="checkbox" id="remember-user">
                        <label for="remember-user">Remember me</label>
                    </div>

                    <a href="#" onclick="showEmailForm()">
                        <p>Forgot your password?</p>
                    </a>
                </div>

                <div class="submit">
                    <button type="submit"><span>Login</span></button>
                </div>
            </form>

            <form action="Login" method="POST">
                <input type="hidden" name="action" value="signup">
                <div class="signup-section">
                    <p>Chưa có tài khoản?</p>
                    <input type="submit" value="Sign Up" class="signup-button">
                </div>
            </form>

            <div id="overlay" class="overlay" style="display: none;" onclick="hideEmailForm()">
                <div class="forgot-password-form" onclick="event.stopPropagation()">
                    <form action="forgot" method="POST">
                        <label for="email">Nhập Gmail:</label>
                        <input type="email" id="email" name="email" required placeholder="nhập Gmail">
                        <button type="submit">Gửi</button>
                    </form>
                </div>
            </div>
            <div class="email-check-message">
                <c:out value="${emailcheck}" />
            </div>

            <script>
                function showEmailForm() {
                    document.getElementById("overlay").style.display = "flex";
                }
                function hideEmailForm() {
                    document.getElementById("overlay").style.display = "none";
                }
            </script>
        </div>
    </body>

</html>
