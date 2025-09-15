package com.duong.service;

import com.duong.entity.Category;

import java.util.List;

public interface CategoryService {
    boolean addCategory(Category category);
    boolean deleteCategory(int id);
    boolean updateCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategory();
}
