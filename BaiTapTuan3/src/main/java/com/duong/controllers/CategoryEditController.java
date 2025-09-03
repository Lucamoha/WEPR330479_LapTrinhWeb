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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
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
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            List<FileItem> items = servletFileUpload.parseRequest((RequestContext) req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("id")) {
                    category.setCateID(Integer.parseInt(item.getString()));
                }
                else if (item.getFieldName().equals("name")) {
                    category.setCateName(item.getString("UTF-8"));
                }
                else if (item.getFieldName().equals("icon")) {
                    if (item.getSize() > 0) {// neu co file d
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;
                        File file = new File(Constant.DIR + "/category/" + fileName);
                        item.write(file);
                        category.setIcon("category/"+fileName);
                    }
                    else {
                        category.setIcon(null);
                    }
                }
            }

            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/category/list");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
