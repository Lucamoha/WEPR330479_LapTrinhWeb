package com.duong.dao.impl;

import com.duong.connection.DBConnect;
import com.duong.dao.CategoryDao;
import com.duong.models.CategoryModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class CategoryDaoImpl implements CategoryDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void insert(CategoryModel category) {
        String sql = "INSERT INTO category(cate_name,icons) VALUES (?,?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(CategoryModel category) {
        String sql = "UPDATE category SET cate_name = ?, icons=? WHERE cate_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getCateID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM category WHERE cate_id = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CategoryModel get(int id) {
        String sql = "SELECT * FROM category WHERE cate_id = ? ";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return CategoryModel.builder()
                        .cateID(rs.getInt("cate_id"))
                        .cateName(rs.getString("cate_name"))
                        .icon(rs.getString("icons"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();}
        return null;
    }

    @Override
    public CategoryModel get(String name) {
        String sql = "SELECT * FROM category WHERE cate_name = ? ";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return CategoryModel.builder()
                        .cateID(rs.getInt("cate_id"))
                        .cateName(rs.getString("cate_name"))
                        .icon(rs.getString("icons"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();}
        return null;
    }

    @Override
    public List<CategoryModel> getAll() {
        List<CategoryModel> categories = new ArrayList<CategoryModel>();
        String sql = "SELECT * FROM Category";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(CategoryModel.builder()
                        .cateID(rs.getInt("cate_id"))
                        .cateName(rs.getString("cate_name"))
                        .icon(rs.getString("icons"))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<CategoryModel> search(String keyword) {
        return List.of();
    }
}
