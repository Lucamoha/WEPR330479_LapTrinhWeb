<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg p-4 text-center" style="max-width: 500px; width: 100%;">
        <h2 class="mb-3 text-primary">
            <i class="fa fa-home"></i> Trang chủ
        </h2>

        <h4 class="mb-4">Xin chào, <span class="text-success fw-bold">${sessionScope.username}</span> 🎉</h4>

        <p class="text-muted">Chúc bạn một ngày làm việc hiệu quả!</p>
    </div>
</div>

</body>
</html>