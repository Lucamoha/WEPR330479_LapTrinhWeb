package com.duong.filter;

import com.duong.util.AppConfig;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/views/admin/*", "/common/admin/*"})
public class AdminAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("role");

        if (role != null && req.getSession().getAttribute("role").equals(AppConfig.get("users.role.admin"))) {
            chain.doFilter(request, response);
            return;
        }
        res.sendRedirect(req.getContextPath() + "/login");
    }
}
