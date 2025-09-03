package com.duong.controllers;

import com.duong.models.UserModel;
import com.duong.services.UserService;
import com.duong.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);

        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Vui lòng điền tài khoản và mật khẩu";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        UserModel user = service.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);

            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu sai";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse res, String username) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(30 * 60);
        res.addCookie(cookie);
    }
}