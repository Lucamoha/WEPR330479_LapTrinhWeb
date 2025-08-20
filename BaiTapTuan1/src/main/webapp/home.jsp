<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 8/20/2025
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String username = (String)session.getAttribute("username");
    if (username == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Xin chào, ${sessionScope.username}</h2>
    <a href="SessionLogout">Logout</a>
</body>
</html>
