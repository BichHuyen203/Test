<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gửi Email</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        
        <style>
            body {
                background-color: #f8f9fa;
            }
            .email-form-container {
                max-width: 600px;
                margin: 50px auto;
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            .btn-custom {
                background-color: #198754;
                color: white;
            }
            .btn-custom:hover {
                background-color: #146c43;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="email-form-container">
                <h2 class="text-center mb-4">📧 Gửi Email</h2>
                
                <form action="SendingEmail" method="post">
                    <div class="mb-3">
                        <label for="TieuDeEmail" class="form-label"><strong>Tiêu Đề</strong></label>
                        <input type="text" class="form-control" id="TieuDeEmail" name="tieuDe" placeholder="Nhập tiêu đề email" required>
                    </div>

                    <div class="mb-3">
                        <label for="NoiDungEmail" class="form-label"><strong>Nội Dung</strong></label>
                        <textarea class="form-control" id="NoiDungEmail" name="noiDung" rows="4" placeholder="Nhập nội dung email" required></textarea>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-custom">
                            <i class="bi bi-envelope-fill"></i> Gửi Email Cho Tất Cả Member
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>