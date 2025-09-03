package com.duong.controllers;

import com.duong.services.UserService;
import com.duong.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/resetPassword")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirmPassword = req.getParameter("confirmPassword");

        String alertMsg = "";

        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || email == null || email.isEmpty()) {
            alertMsg = "Vui lòng điền đầy đủ tường";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            alertMsg = "Mật khẩu không giống nhau";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        if (!service.checkExistEmail(email)) {
            alertMsg = "Email không tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
            return;
        }

        if (service.resetPassword(email, password)) {
            alertMsg = "Đổi mật khẩu thành công! Vui lòng đăng nhập lại";
            req.getSession().setAttribute("alert", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        else {
            alertMsg = "Đổi mật khẩu thất bại! Lỗi hệ thống";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
        }
    }
}
