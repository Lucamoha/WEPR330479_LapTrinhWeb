<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/3/2025
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Category</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4 text-primary">Danh sách Category</h2>

    <table class="table table-bordered table-striped text-center align-middle">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Tên Category</th>
            <th>Ảnh đại diện</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cateList}" var="cate" varStatus="st">
            <tr>
                <td>${st.index + 1}</td>
                <td>${cate.cateName}</td>
                <td>
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"/>
                    <img src="${imgUrl}" width="100" height="80" class="img-thumbnail"/>
                </td>
                <td>
                    <a href="<c:url value='/category/edit?id=${cate.cateID}'/>" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="<c:url value='/category/delete?id=${cate.cateID}'/>" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="<c:url value='/category/add'/>" class="btn btn-success">
        + Thêm Category mới
    </a>
</div>

</body>
</html>
