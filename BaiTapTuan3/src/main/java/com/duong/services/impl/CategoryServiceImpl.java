package com.duong.services.impl;

import com.duong.dao.CategoryDao;
import com.duong.dao.impl.CategoryDaoImpl;
import com.duong.models.CategoryModel;
import com.duong.services.CategoryService;

import java.io.File;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(CategoryModel category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(CategoryModel newCategory) {
        CategoryModel oldCategory = categoryDao.get(newCategory.getCateID());
        oldCategory.setCateName(newCategory.getCateName());
        if (newCategory.getIcon() != null) {
            String fileName = oldCategory.getIcon();
            final String dir = "E:\\upload";
            File file = new File(dir + "/category" + fileName);
            if (file.exists()) {
                file.delete();
            }
            oldCategory.setIcon(newCategory.getIcon());
        }
        categoryDao.edit(oldCategory);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public CategoryModel get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public CategoryModel get(String name) {
        return categoryDao.get(name);
    }

    @Override
    public List<CategoryModel> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<CategoryModel> search(String keyword) {
        return categoryDao.search(keyword);
    }
}
