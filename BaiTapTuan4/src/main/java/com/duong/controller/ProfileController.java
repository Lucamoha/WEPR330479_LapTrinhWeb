package com.duong.controller;

import com.duong.entity.User;
import com.duong.service.UserService;
import com.duong.service.impl.UserServiceImpl;
import com.duong.util.AppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@WebServlet(urlPatterns = {"/profile", "/profiles"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 20
)
public class ProfileController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);

        req.setAttribute("user", user);
        if (user.getRole().equals(AppConfig.get("users.role.admin"))) {
            req.getRequestDispatcher("/views/admin/profile.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);

        String fullName = req.getParameter("fullName");
        user.setFullName(fullName != null ? fullName.trim() : null);

        String phone = req.getParameter("phone");
        user.setPhone(phone != null ? phone.trim() : null);

        String oldImage = req.getParameter("oldImage");
        Part imagePart = req.getPart("image");

        String uploadDirPath = AppConfig.get("db.uploadDirectory");
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String savedFileName = oldImage;
        if (imagePart != null && imagePart.getSize() > 0) {
            String submitted = Paths.get(Objects.toString(imagePart.getSubmittedFileName(), "")).getFileName().toString();
            String ext = "";
            int dot = submitted.lastIndexOf('.');
            if (dot >= 0) ext = submitted.substring(dot + 1);

            savedFileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : ("." + ext));
            File dest = new File(uploadDir, savedFileName);
            imagePart.write(dest.getAbsolutePath());

            if (oldImage != null && !oldImage.isBlank() && !oldImage.equals(savedFileName)) {
                File old = new File(uploadDir, oldImage);
                if (old.exists()) old.delete();
            }
        }
        user.setImage(savedFileName);

        boolean ok = userService.updateUser(user);
        if (ok) {
            req.setAttribute("alert", "Cập nhật hồ sơ thành công!");
            req.setAttribute("alertType", "success");
        } else {
            req.setAttribute("alert", "Cập nhật thất bại!");
            req.setAttribute("alertType", "danger");
        }
        req.setAttribute("user", userService.getUserByUsername(username));

        if (user.getRole().equals(AppConfig.get("users.role.admin"))) {
            req.getRequestDispatcher("/views/admin/profile.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
        }
    }
}
