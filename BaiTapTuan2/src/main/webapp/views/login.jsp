<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/27/2025
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    <h2>Nhập tên và mật khẩu để đăng nhập</h2>
    <c:if test="${alert !=null}">
    <h3 class="alert alertdanger">${alert}</h3>
    </c:if>
    <section>
        <label class="input login-input">
            <div class="input-group">
					<span class="input-group-addon"><i class="fa
fa-user"></i></span>
                <input type="text" placeholder="Tài khoản" name="username"
                       class="form-control">
            </div>
        </label> <label class="input login-input">
        <div class="input-group">
					<span class="input-group-addon"><i class="fa
fa-user"></i></span>
            <input type="password" placeholder="Mật khẩu" name="password"
                   class="form-control">
        </div>
    </label>

    </section>
    <!-- Nút submit -->
    <div class="d-grid">
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </div>
</body>
</html>
