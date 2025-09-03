<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang Chủ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5 text-center">

<h1 class="mb-4">Chào mừng bạn đến với Website</h1>

<a href="${pageContext.request.contextPath}/login" class="btn btn-primary btn-lg">
    Đăng nhập
</a>
<a href="${pageContext.request.contextPath}/register" class="btn btn-primary btn-lg">
    Đăng ký
</a>
</body>
</html>