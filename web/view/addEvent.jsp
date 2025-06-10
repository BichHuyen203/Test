<%@page import="java.util.ArrayList" %>
<%@page import="model.Clubs" %>
<%@page import="dao.ClubDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Sự Kiện</title>
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome for Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/addevent.css">
    </head>
    <body>
        <div class="container mt-5">
            <div class="event-box p-4 shadow-lg bg-white rounded">
                <h3 class="text-center mb-4">
                    <i class="fas fa-calendar-plus"></i> Thêm Sự Kiện
                </h3>
                <form action="EventP" method="POST">
                    <% ArrayList<Clubs> club = ClubDAO.GetNameClub(); %>
                    <input type="hidden" name="event" value="addev">

                    <div class="mb-3">
                        <label for="eventName" class="form-label">
                            <i class="fas fa-heading"></i> Tên sự kiện:
                        </label>
                        <input type="text" id="eventName" name="EventName" class="form-control" placeholder="Nhập tên sự kiện">
                    </div>

                    <div class="mb-3">
                        <label for="eventDesc" class="form-label">
                            <i class="fas fa-align-left"></i> Mô tả:
                        </label>
                        <input type="text" id="eventDesc" name="Des" class="form-control" placeholder="Nhập mô tả sự kiện">
                    </div>

                    <div class="mb-3">
                        <label for="eventDateTime" class="form-label">
                            <i class="fas fa-calendar-alt"></i> Chọn ngày và giờ bắt đầu:
                        </label>
                        <input type="datetime-local" id="eventDateTime" name="eventDateTime" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="endDate" class="form-label">
                            <i class="fas fa-calendar-times"></i> Chọn ngày kết thúc:
                        </label>
                        <input type="datetime-local" id="endDate" name="enddate" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="eventClub" class="form-label">
                            <i class="fas fa-users"></i> Chọn Câu lạc bộ:
                        </label>
                        <select name="club" id="eventClub" class="form-select">
                            <% for (Clubs role : club) { %>
                                <option value="<%= role.getClubID() %>"><%= role.getClubName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-plus-circle"></i> Thêm Sự Kiện
                        </button>
                    </div>
                </form>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>