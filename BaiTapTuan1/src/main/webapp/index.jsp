<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h2>Username and Password</h2>
Username: admin <br>
Password: 123 <br>

<h1>Cookie Login</h1>
<form action="CookieLogin" method="POST">
    Username: <input type="text" name="username"> <br/>
    Password: <input type="text" name="password"> <br/>
    <input type="submit" value="Submit" />
</form>
<br>
<h1>Session Login</h1>
<form action="SessionLogin" method="POST">
    Username: <input type="text" name="username"> <br/>
    Password: <input type="text" name="password"> <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>