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
                        .email(rs.getString("email"))
                        .build();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String query = "select * from [users] where email = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception ex) {}
        return duplicate;
    }

    @Override
    public boolean register(String username, String email, String password) {
        String sql = "INSERT INTO [users] VALUES (?, ?, ?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean resetPassword(String email, String password) {
        String sql = "UPDATE [users] SET password = ? WHERE email = ?";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}