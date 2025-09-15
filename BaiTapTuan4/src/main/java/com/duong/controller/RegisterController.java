package com.duong.controller;

import com.duong.entity.User;
import com.duong.service.UserService;
import com.duong.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/register", "/signup", "/dangky"})
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");

        String alertMsg = "";

        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty() ||
                email == null || email.isEmpty()) {
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

        if (service.getUserByEmail(email) != null) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        User user = service.getUserByUsername(username);
        if (user == null) {
            boolean Success = service.registerUser(User.builder()
                            .email(email)
                            .password(password)
                            .fullName(fullName)
                            .phone(phone)
                            .username(username)
                            .build());
            if (Success) {
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
}
