package com.duong.dao;

import com.duong.models.UserModel;

public interface UserDao {
    UserModel getUser(String username);
    boolean checkExistEmail(String email);
    boolean register(String username, String email, String password);
    boolean resetPassword(String email, String password);
}