package com.duong.service.impl;

import com.duong.dao.CategoryDao;
import com.duong.dao.impl.CategoryDaoImpl;
import com.duong.entity.Category;
import com.duong.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public boolean addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryDao.deleteCategory(id);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }
}
