<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<div class="container d-flex justify-content-center align-items-center vh-100">
<div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
  <h3 class="text-center mb-4">Đăng nhập</h3>

  <c:if test="${alert != null}">
    <div class="alert alert-danger text-center" role="alert">
        ${alert}
    </div>
  </c:if>

  <form action="login" method="post">
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

    <!-- Remember Me -->
    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" name="remember" id="remember">
      <label class="form-check-label" for="remember">Nhớ tôi</label>
    </div>

    <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

    <div class="mt-3 text-center">
      <a href="resetPassword">Quên mật khẩu?</a> |
      <a href="register">Đăng ký</a>
    </div>
  </form>
</div>
</div>