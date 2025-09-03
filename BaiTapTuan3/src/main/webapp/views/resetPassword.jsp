<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
    <h3 class="text-center mb-4">Đặt lại mật khẩu</h3>

    <!-- Thông báo -->
    <c:if test="${alert != null}">
      <div class="alert alert-danger text-center" role="alert">
          ${alert}
      </div>
    </c:if>

    <form action="resetPassword" method="post">
      <!-- Email -->
      <div class="mb-3">
        <label class="form-label">Email</label>
        <div class="input-group">
          <span class="input-group-text"><i class="fa fa-user"></i></span>
          <input type="text" class="form-control" name="email" placeholder="Nhập email">
        </div>
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <div class="input-group">
          <span class="input-group-text"><i class="fa fa-lock"></i></span>
          <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu">
        </div>
      </div>

      <!-- Confirm Password -->
      <div class="mb-3">
        <label class="form-label">Nhập lại mật khẩu</label>
        <div class="input-group">
          <span class="input-group-text"><i class="fa fa-lock"></i></span>
          <input type="password" class="form-control" name="confirmPassword" placeholder="Nhập lại mật khẩu">
        </div>
      </div>

      <!-- Nút submit -->
      <div class="d-grid">
        <button type="submit" class="btn btn-primary">
          <i class="fa fa-sign-in-alt"></i> Đổi mật khẩu
        </button>
      </div>
    </form>
  </div>
</div>

</body>
</html>