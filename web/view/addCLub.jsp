<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Câu Lạc Bộ</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="css/addclub.css"> <!-- File CSS bên ngoài -->
    </head>
    <div class="club-form-container mt-5">
            <h3 class="text-center"><i class="fa-solid fa-plus-circle"></i> Thêm Câu Lạc Bộ</h3>
            <form action="club" method="POST">
                <div class="mb-3">
                    <label class="form-label">Tên Câu Lạc Bộ:</label>
                    <input type="text" class="form-control" name="clubname" placeholder="Nhập tên câu lạc bộ">
                </div>
                <div class="mb-3">
                    <label class="form-label">Mô Tả:</label>
                    <input type="text" class="form-control" name="des" placeholder="Nhập mô tả ngắn gọn">
                </div>
                <div class="mb-3">
                    <label class="form-label">Ngày Thành Lập:</label>
                    <input type="date" class="form-control" name="date" id="establishedDate" readonly>
                </div>
                <button type="submit" class="btn btn-submit"><i class="fa-solid fa-check"></i> Tạo</button>
            </form>
        </div>
        <script>
            document.getElementById('establishedDate').value = new Date().toISOString().split('T')[0];
        </script>
</html>
