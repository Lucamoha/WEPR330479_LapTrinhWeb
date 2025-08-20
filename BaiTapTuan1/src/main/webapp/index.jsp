<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #40531A;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin: 30px 0 10px;
            color: #C7D59F;
        }

        .info-box {
            text-align: center;
            margin-top: 10px;
            color: #fff;
            font-size: 15px;
        }

        .info-box span {
            display: inline-block;
            background: #C7D59F;
            color: #000;
            padding: 5px 10px;
            border-radius: 8px;
            margin: 3px;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            gap: 40px;
            padding: 60px;
        }

        .card {
            background: #C7D59F;
            border-radius: 15px;
            padding: 30px 40px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.25);
            width: 350px;
            text-align: center;
            transition: transform 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        h1 {
            font-size: 22px;
            margin-bottom: 20px;
            color: #2C3A0C;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
            color: #2C3A0C;
            font-weight: bold;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #40531A;
            margin-bottom: 15px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #2C3A0C;
            outline: none;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border-radius: 8px;
            border: none;
            background: #40531A;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        input[type="submit"]:hover {
            background: #2C3A0C;
        }
    </style>
</head>
<body>
<h2>Username and Password</h2>
<div class="info-box">
    <span>Username: admin</span>
    <span>Password: 123</span>
</div>

<div class="container">
    <div class="card">
        <h1>Cookie Login</h1>
        <form action="CookieLogin" method="POST">
            <label for="cookie-username">Username</label>
            <input type="text" id="cookie-username" name="username" required>

            <label for="cookie-password">Password</label>
            <input type="password" id="cookie-password" name="password" required>

            <input type="submit" value="Login">
        </form>
    </div>

    <div class="card">
        <h1>Session Login</h1>
        <form action="SessionLogin" method="POST">
            <label for="session-username">Username</label>
            <input type="text" id="session-username" name="username" required>

            <label for="session-password">Password</label>
            <input type="password" id="session-password" name="password" required>

            <input type="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>
