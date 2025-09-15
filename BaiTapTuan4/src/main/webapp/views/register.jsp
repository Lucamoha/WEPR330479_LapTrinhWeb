<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>


<div class="container d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow-lg p-4" style="max-width: 500px; width: 100%;">
    <h3 class="text-center mb-4">Đăng ký tài khoản</h3>

    <c:if test="${alert != null}">
      <div class="alert alert-danger text-center" role="alert">
          ${alert}
      </div>
    </c:if>

    <form action="register" method="post">
      <!-- Username -->
      <div class="mb-3">
        <label class="form-label">Tài khoản</label>
        <input type="text" class="form-control" name="username" placeholder="Nhập tài khoản" required>
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu" required>
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <input type="password" class="form-control" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
      </div>

      <!-- Full Name -->
      <div class="mb-3">
        <label class="form-label">Họ và tên</label>
        <input type="text" class="form-control" name="fullName" placeholder="Nhập họ và tên">
      </div>

      <!-- Phone -->
      <div class="mb-3">
        <label class="form-label">Số điện thoại</label>
        <input type="tel" class="form-control" name="phone" placeholder="Nhập số điện thoại">
      </div>

      <!-- Email -->
      <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" class="form-control" name="email" placeholder="Nhập email" required>
      </div>

      <button type="submit" class="btn btn-success w-100">Đăng ký</button>

      <div class="mt-3 text-center">
        <a href="login">Quay lại đăng nhập</a>
      </div>
    </form>
  </div>
</div>

