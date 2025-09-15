package com.duong.dao;

import com.duong.entity.Category;

import java.util.List;

public interface CategoryDao {
    boolean addCategory(Category category);
    boolean deleteCategory(int id);
    boolean updateCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategory();
}
