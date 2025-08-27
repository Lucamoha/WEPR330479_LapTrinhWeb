package com.duong.dao.impl;

import com.duong.connection.DBConnect;
import com.duong.models.UserModel;
import com.duong.dao.UserDao;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserDaoImpl implements UserDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public UserModel getUser(String username) {
        String sql = "SELECT * FROM [users] WHERE username = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return UserModel.builder()
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean register(String username, String password) {
        String sql = "INSERT INTO [users] VALUES (?, ?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}