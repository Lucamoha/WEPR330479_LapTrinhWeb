package com.duong.controllers;

import com.duong.models.CategoryModel;
import com.duong.services.CategoryService;
import com.duong.services.impl.CategoryServiceImpl;
import com.duong.utils.Constant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@WebServlet(urlPatterns = { "/category/edit" })
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CategoryModel category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/editCategory.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryModel category = new CategoryModel();

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        upload.setHeaderCharset(Charset.forName("UTF-8"));

        try {
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            List<FileItem> items = upload.parseRequest(req);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "id":
                            category.setCateID(Integer.parseInt(item.getString()));
                            break;
                        case "name":
                            category.setCateName(item.getString(Charset.forName("UTF-8")));
                            break;
                    }
                } else {
                    if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
                        String originalFileName = item.getName();
                        String ext = "";
                        int index = originalFileName.lastIndexOf(".");
                        if (index > 0) {
                            ext = originalFileName.substring(index + 1);
                        }
                        String fileName = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);
                        File file = new File(Constant.DIR + "/category", fileName);

                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }

                        item.write(file.toPath());
                        category.setIcon("category/" + fileName);
                    } else {
                        CategoryModel old = cateService.get(category.getCateID());
                        if (old != null) {
                            category.setIcon(old.getIcon());
                        }
                    }
                }
            }

            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/category/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
