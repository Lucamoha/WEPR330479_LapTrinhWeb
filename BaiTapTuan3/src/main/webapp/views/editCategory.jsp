<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa Category</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="text-primary mb-4">Chỉnh sửa Category</h2>

        <!-- Form sửa category -->
        <form action="${pageContext.request.contextPath}/category/edit"
              method="post" enctype="multipart/form-data">

            <!-- ID (ẩn) -->
            <input type="hidden" name="id" value="${category.cateID}"/>

            <!-- Tên category -->
            <div class="mb-3">
                <label for="name" class="form-label">Tên Category</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${category.cateName}" required>
            </div>

            <!-- Ảnh hiện tại -->
            <div class="mb-3">
                <label class="form-label">Ảnh hiện tại</label><br>
                <c:url value="/image?fname=${category.icon}" var="imgUrl"/>
                <img src="${imgUrl}" alt="Category icon" width="120" class="img-thumbnail mb-2">
            </div>

            <!-- Upload ảnh mới -->
            <div class="mb-3">
                <label for="icon" class="form-label">Chọn ảnh mới (nếu muốn thay)</label>
                <input type="file" class="form-control" id="icon" name="icon" accept="image/*">
            </div>

            <!-- Nút -->
            <button type="submit" class="btn btn-warning">
                <i class="fa fa-save"></i> Cập nhật
            </button>
            <a href="${pageContext.request.contextPath}/category/list" class="btn btn-secondary">
                Quay lại
            </a>
        </form>
    </div>
</div>

</body>
</html>
