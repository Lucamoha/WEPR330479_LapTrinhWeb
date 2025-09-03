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
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/category/add" })
public class CategoryAddController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/addCategory.jsp");
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
            List<FileItem> items = servletFileUpload.parseRequest((javax.servlet.http.HttpServletRequest) req);
            for (FileItem item : items) {
                if (item.getFieldName().equals("name")) {
                    category.setCateName(item.getString("UTF-8"));
                }
                else if (item.getFieldName().equals("icon")) {
                    String originalFileName = item.getName();
                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;
                    File file = new File(Constant.DIR + "/category/" + fileName);
                    item.write(file);
                    category.setIcon("category/"+fileName);
                }
            }
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/category/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
