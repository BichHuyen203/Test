<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %> <!-- Thay bằng package thực tế chứa class User -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chat</title>
        <link rel="stylesheet" href="/Test/css/chatcss.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    </head>
    <% ArrayList<User> user = (ArrayList<User>)(session.getAttribute("user")); %>


        <button id="chat-icon" class="chat-icon">
    <i class="bi bi-chat-dots-fill"></i>
</button>

    <div id="chat-modal" class="chat-modal">
        <div class="modal-content">
            <span class="close" id="close-modal">&times;</span>
            <h2>Messages</h2>
            <div id="messages"></div>
            <div class="input-container">
                <textarea id="message-input" placeholder="Type your message..." maxlength="2000"></textarea>
                <button id="send-button">Send</button>
            </div>
        </div>
    </div>

    <script>
        var modal = document.getElementById("chat-modal");
        var btn = document.getElementById("chat-icon");
        var span = document.getElementById("close-modal");
        var messagesDiv = document.getElementById("messages");
        var messageInput = document.getElementById("message-input");
        var sendButton = document.getElementById("send-button");

        var fullName = "<%= user.get(0).getFullName() %>";
        var role = "<%= user.get(0).getRole() %>";

        btn.onclick = function () {
            modal.style.display = "block";
            loadMessages();
        }

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }

        function loadMessages() {
            var messages = JSON.parse(localStorage.getItem('messages')) || [];
            messagesDiv.innerHTML = '';

            messages.forEach(function (msg, index) {
                var messageElement = document.createElement("div");
                messageElement.classList.add("message");

                var userInfo = document.createElement("div");
                userInfo.classList.add("user-info");
                userInfo.textContent = msg.fullName + " (" + msg.role + ")";

                var messageText = document.createElement("div");
                messageText.textContent = msg.text;

                var deleteButton = document.createElement("div");
                deleteButton.classList.add("delete-btn");

                if (msg.fullName === fullName || role === "Admin") {
                    deleteButton.textContent = "Delete";
                    deleteButton.onclick = function () {
                        deleteMessage(index);
                    };
                } else {
                    deleteButton.style.color = "gray";
                }

                messageElement.appendChild(userInfo);
                messageElement.appendChild(messageText);
                messageElement.appendChild(deleteButton);

                messagesDiv.appendChild(messageElement);
            });
        }

        setInterval(function () {
            if (modal.style.display === "block") {
                loadMessages();
            }
        }, 2000);

        function deleteMessage(index) {
            var messages = JSON.parse(localStorage.getItem('messages')) || [];
            messages.splice(index, 1);
            localStorage.setItem('messages', JSON.stringify(messages));
            loadMessages();
        }

        sendButton.onclick = function () {
            var newMessage = messageInput.value.trim();
            if (newMessage !== "") {
                var messages = JSON.parse(localStorage.getItem('messages')) || [];
                var newMessageData = {
                    text: newMessage,
                    fullName: fullName,
                    role: role
                };
                messages.push(newMessageData);
                localStorage.setItem('messages', JSON.stringify(messages));
                messageInput.value = '';
                loadMessages();
            }
        };

        window.onload = function () {
            modal.style.display = "none";
        };
    </script>
</html>