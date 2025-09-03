package com.duong.controllers;

import com.duong.models.CategoryModel;
import com.duong.services.CategoryService;
import com.duong.services.impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category/list")
public class CategoryController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryModel> cateList = cateService.getAll();
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/listCategory.jsp");
        dispatcher.forward(req, resp);
    }
}
