<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<div class="container mt-5">
  <h2 class="mb-4 text-primary">Danh sách Category</h2>

  <table class="table table-bordered table-striped text-center align-middle">
    <thead class="table-dark">
    <tr>
      <th>#</th>
      <th>Tên Category</th>
      <th>Icon</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cateList}" var="cate" varStatus="st">
      <tr>
        <td>${st.index + 1}</td>
        <td>${cate.name}</td>
        <td>
          <img src="${pageContext.request.contextPath}/file/${cate.icon}"
               width="100" height="80" class="img-thumbnail"/>
        </td>
        <td>
          <a href="<c:url value='/admin/category/update?id=${cate.id}'/>" class="btn btn-warning btn-sm">Sửa</a>
          <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.id}&icon=${cate.icon}" class="btn btn-danger btn-sm"
             onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <a href="<c:url value='/admin/category/add'/>" class="btn btn-success">
    + Thêm Category mới
  </a>
</div>