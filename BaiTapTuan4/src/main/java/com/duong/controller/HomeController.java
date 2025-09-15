package com.duong.controller;

import com.duong.util.AppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "/trangchu", "/admin/home", "/admin/trangchu"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (role != null && role.equals(AppConfig.get("users.role.admin"))) {
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
        }
    }
}
