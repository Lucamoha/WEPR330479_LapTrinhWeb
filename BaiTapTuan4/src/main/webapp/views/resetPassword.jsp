<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<div class="container d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
    <h3 class="text-center mb-4">Đặt lại mật khẩu</h3>

    <form action="resetPassword" method="post">
      <div class="mb-3">
        <label class="form-label">Nhập email của bạn</label>
        <input type="email" class="form-control" name="email" placeholder="example@email.com" required>
      </div>

      <button type="submit" class="btn btn-primary w-100">Gửi liên kết đặt lại</button>

      <div class="mt-3 text-center">
        <a href="login">Quay lại đăng nhập</a>
      </div>
    </form>
  </div>
</div>