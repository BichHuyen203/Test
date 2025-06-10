<%-- 
    Document   : EventCheck
    Created on : Mar 14, 2025, 11:10:00 PM
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giấy Khảo Sát</title>
        <link rel="stylesheet" href="css/eventCheck.css">
    </head>
    <body>
        <div class="survey-container">
            <h2>Khảo Sát Sau Sự Kiện</h2>
            <p class="subtitle">Cảm Nghĩ Sau Khi Tham Gia Sự Kiện</p>

            <% ArrayList<Events> ev = EventDAO.GetAllEvent(); %>

            <form action="uploadImage" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="feedback">Cảm Nghĩ Của Bạn Về Sự Kiện Của Chúng Tôi</label>
                    <textarea class="input-field" id="feedback" name="feedback" rows="3" placeholder="Nhập cảm nghĩ của bạn..."></textarea>
                </div>

                <div class="form-group">
                    <label>Hình Ảnh</label>
                    <div class="image-upload">
                        <label for="nameimg">Nội dung:</label>
                        <input type="text" id="nameimg" name="nameimg">
                    </div>
                    <div class="image-upload">
                        <label for="image" class="custom-file-upload">Chọn tệp</label>
                        <input type="file" id="image" name="image" style="display: none;">
                        <span id="file-name">Không có tệp nào được chọn</span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="event">Sự Kiện:</label>
                    <select id="event" name="event">
                        <% for (Events e : ev) { %>
                        <option value="<%= e.getEventID() %>"><%= e.getEventName() %></option>
                        <% } %>
                    </select>
                </div>

                <button type="submit" class="submit-btn">Gửi Khảo Sát</button>
            </form>
        </div>

        <script>
            document.getElementById('image').addEventListener('change', function (e) {
                const fileName = e.target.files.length > 0 ? e.target.files[0].name : 'Không có tệp nào được chọn';
                document.getElementById('file-name').textContent = fileName;
            });
        </script>
    </body>
</html>