<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <!-- Logo -->
    <a class="navbar-brand" href="${cp}/admin/home">duong.com</a>

    <!-- Nút toggle khi responsive -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Menu -->
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link" href="${cp}/admin/category">Quản lý Category</a></li>
      </ul>

      <!-- Dropdown tài khoản -->
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="accountMenu"
             role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="fa fa-user-circle me-1"></i>
            <c:choose>
              <c:when test="${not empty sessionScope.username}">
                ${sessionScope.username}
              </c:when>
              <c:otherwise>
                Tài khoản
              </c:otherwise>
            </c:choose>
          </a>

          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="accountMenu">
            <c:choose>
              <c:when test="${not empty sessionScope.username}">
                <li>
                  <a class="dropdown-item" href="${cp}/profile">
                    <i class="fa fa-id-card me-2"></i>Thông tin cá nhân
                  </a>
                </li>
                <li><hr class="dropdown-divider"></li>
                <li>
                  <a class="dropdown-item text-danger" href="${cp}/logout">
                    <i class="fa fa-sign-out-alt me-2"></i>Đăng xuất
                  </a>
                </li>
              </c:when>

              <c:otherwise>
                <li>
                  <a class="dropdown-item" href="${cp}/login">
                    <i class="fa fa-sign-in-alt me-2"></i>Đăng nhập
                  </a>
                </li>
                <li>
                  <a class="dropdown-item" href="${cp}/register">
                    <i class="fa fa-user-plus me-2"></i>Đăng ký
                  </a>
                </li>
              </c:otherwise>
            </c:choose>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>