package com.duong.dao;

import com.duong.models.CategoryModel;

import java.util.List;

public interface CategoryDao {
    void insert(CategoryModel category);
    void edit(CategoryModel category);
    void delete(int id);
    CategoryModel get(int id);
    CategoryModel get(String name);
    List<CategoryModel> getAll();
    List<CategoryModel> search(String keyword);
}
