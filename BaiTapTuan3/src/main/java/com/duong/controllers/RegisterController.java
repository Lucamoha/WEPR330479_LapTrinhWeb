package com.duong.controllers;

import com.duong.models.UserModel;
import com.duong.services.UserService;
import com.duong.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirmPassword = req.getParameter("confirmPassword");

        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || email == null || email.isEmpty()) {
            alertMsg = "Vui lòng điền đầy đủ tường";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            alertMsg = "Mật khẩu không giống nhau";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        UserModel user = service.getUser(username);
        if (user == null) {
            boolean Succes = service.register(username, email, password);
            if (Succes) {
                alertMsg = "Đăng ký thành công! Vui lòng đăng nhập lại";
                req.getSession().setAttribute("alert", alertMsg);
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            else {
                alertMsg = "Đăng ký thất bại! Lỗi hệ thống";
                req.setAttribute("alert", alertMsg);
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            }
        }
        else {
            alertMsg = "Username " + username + " đã tồn tại";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
}
