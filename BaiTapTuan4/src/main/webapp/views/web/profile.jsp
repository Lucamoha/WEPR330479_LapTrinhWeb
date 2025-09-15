<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<div class="container py-4">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card shadow-sm">
        <div class="card-header bg-dark text-white">
          <h5 class="mb-0">Hồ sơ cá nhân</h5>
        </div>
        <div class="card-body">
          <c:if test="${not empty alert}">
            <div class="alert alert-${alertType != null ? alertType : 'info'}">${alert}</div>
          </c:if>

          <form action="${cp}/profile" method="post" enctype="multipart/form-data">
            <!-- Hiển thị email & username (readonly) -->
            <div class="row">
              <div class="col-md-6 mb-3">
                <label class="form-label">Email</label>
                <input type="text" class="form-control" value="${user.email}" readonly>
              </div>
              <div class="col-md-6 mb-3">
                <label class="form-label">Username</label>
                <input type="text" class="form-control" value="${user.username}" readonly>
              </div>
            </div>

            <!-- Phone -->
            <div class="mb-3">
              <label class="form-label">Sđt</label>
              <input name="phone" type="text" class="form-control" value="${user.phone}">
            </div>

            <!-- Full name -->
            <div class="mb-3">
              <label class="form-label">Họ và tên</label>
              <input name="fullName" type="text" class="form-control" value="${user.fullName}">
            </div>

            <!-- Ảnh đại diện -->
            <div class="mb-3">
              <label class="form-label d-block">Ảnh đại diện</label>

              <div class="d-flex align-items-center gap-3">
                <img
                        src="${pageContext.request.contextPath}/file/${user.image}"
                        alt="avatar"
                        class="rounded"
                        style="width:96px;height:96px;object-fit:cover;border:1px solid #ddd;">
                <div class="flex-grow-1">
                  <input class="form-control" type="file" name="image" accept="image/*">
                  <div class="form-text">Chọn ảnh mới (tùy chọn). Bỏ qua để giữ ảnh hiện tại.</div>
                </div>
              </div>
              <!-- giữ file cũ để server biết nếu không upload ảnh mới -->
              <input type="hidden" name="oldImage" value="${user.image}">
            </div>

            <div class="d-flex justify-content-end">
              <a href="${cp}/home" class="btn btn-outline-secondary me-2">Hủy</a>
              <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
