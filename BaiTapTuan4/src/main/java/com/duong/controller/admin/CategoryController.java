package com.duong.controller.admin;

import com.duong.entity.Category;
import com.duong.service.CategoryService;
import com.duong.service.impl.CategoryServiceImpl;
import com.duong.util.AppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/category",
                            "/admin/category/add",
                            "/admin/category/delete",
                            "/admin/category/update"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class CategoryController extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        String icon;
        boolean ok;
        switch (req.getServletPath()) {
            case "/admin/category/add":
                req.getRequestDispatcher("/views/admin/category/addCategory.jsp").forward(req, resp);
                break;

            case "/admin/category/delete":
                id = req.getParameter("id");
                icon = req.getParameter("icon");

                ok = categoryService.deleteCategory(Integer.parseInt(id));

                if (ok) {
                    if (icon != null) {
                        String uploadPath = AppConfig.get("db.uploadDirectory");
                        String safeName = java.nio.file.Paths.get(icon).getFileName().toString();
                        File f = new File(uploadPath, safeName);
                        f.delete();
                    }
                }

                resp.sendRedirect(req.getContextPath() + "/admin/category");
                break;

            case "/admin/category/update":
                id = req.getParameter("id");
                Category category = categoryService.getCategoryById(Integer.parseInt(id));
                req.setAttribute("category", category);
                req.getRequestDispatcher("/views/admin/category/updateCategory.jsp").forward(req, resp);
                break;

            default:
                List<Category> cateList = categoryService.getAllCategory();
                req.setAttribute("cateList", cateList);
                req.getRequestDispatcher("/views/admin/category/listCategory.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getServletPath();
        String uploadPath = AppConfig.get("db.uploadDirectory");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        switch (uri) {
            case "/admin/category/add": {
                req.setCharacterEncoding("UTF-8");
                String name = req.getParameter("name");

                Category category = new Category();
                category.setName(name);

                Part part = req.getPart("icon");
                if (part != null && part.getSize() > 0) {
                    String submitted = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = "";
                    int dot = submitted.lastIndexOf('.');
                    if (dot >= 0 && dot < submitted.length() - 1) {
                        ext = submitted.substring(dot + 1);
                    }

                    String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);
                    File dest = new File(uploadDir, fileName);
                    part.write(dest.getAbsolutePath());

                    category.setIcon(fileName);
                } else {
                    category.setIcon(null);
                }

                boolean ok = categoryService.addCategory(category);
                if (ok) {
                    resp.sendRedirect(req.getContextPath() + "/admin/category");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Thêm category thất bại");
                }
                break;
            }

            case "/admin/category/update": {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                Category category = categoryService.getCategoryById(id);
                if (category != null) {
                    category.setName(name);

                    Part filePart = req.getPart("icon");
                    if (filePart != null && filePart.getSize() > 0) {
                        if (category.getIcon() != null) {
                            File oldFile = new File(uploadPath, category.getIcon());
                            if (oldFile.exists()) oldFile.delete();
                        }
                        String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        String ext = "";
                        int dot = submitted.lastIndexOf('.');
                        if (dot >= 0 && dot < submitted.length() - 1) {
                            ext = submitted.substring(dot + 1);
                        }
                        String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);
                        filePart.write(new File(uploadPath, fileName).getAbsolutePath());
                        category.setIcon(fileName);
                    }

                    boolean ok = categoryService.updateCategory(category);
                    if (ok) {
                        resp.sendRedirect(req.getContextPath() + "/admin/category");
                    } else {
                        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Cập nhật thất bại");
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category không tồn tại");
                }
                break;
            }

            default:
                resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }
}
