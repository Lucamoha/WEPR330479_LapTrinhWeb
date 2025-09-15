<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="text-primary mb-4">Chỉnh sửa Category</h2>

        <!-- Form sửa category -->
        <form action="${pageContext.request.contextPath}/admin/category/update"
              method="post" enctype="multipart/form-data">

            <!-- ID (ẩn) -->
            <input type="hidden" name="id" value="${category.id}"/>

            <!-- Tên category -->
            <div class="mb-3">
                <label for="name" class="form-label">Tên Category</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${category.name}" required>
            </div>

            <!-- Ảnh hiện tại -->
            <div class="mb-3">
                <label class="form-label">Ảnh hiện tại</label><br>
                <img src="${pageContext.request.contextPath}/file/${category.icon}"
                     width="100" height="80" class="img-thumbnail"/>
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
            <a href="${pageContext.request.contextPath}/admin/category" class="btn btn-secondary">
                Quay lại
            </a>
        </form>
    </div>
</div>