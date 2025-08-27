package com.duong.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/views/home.jsp");
        }
    }
}