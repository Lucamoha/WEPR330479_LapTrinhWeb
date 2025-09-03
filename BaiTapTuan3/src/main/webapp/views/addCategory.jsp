<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Category</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="text-primary mb-4">Thêm Category mới</h2>

        <!-- Form thêm category -->
        <form action="${pageContext.request.contextPath}/category/add"
              method="post" enctype="multipart/form-data">

            <!-- Tên category -->
            <div class="mb-3">
                <label for="name" class="form-label">Tên Category</label>
                <input type="text" class="form-control" id="name" name="name"
                       placeholder="Nhập tên category" required>
            </div>

            <!-- Ảnh đại diện -->
            <div class="mb-3">
                <label for="icon" class="form-label">Ảnh đại diện</label>
                <input type="file" class="form-control" id="icon" name="icon" accept="image/*" required>
            </div>

            <button type="submit" class="btn btn-success">
                <i class="fa fa-plus"></i> Thêm
            </button>
            <a href="${pageContext.request.contextPath}/category/list" class="btn btn-secondary">
                Quay lại
            </a>
        </form>
    </div>
</div>

</body>
</html>
