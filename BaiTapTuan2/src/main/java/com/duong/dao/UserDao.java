package com.duong.dao;

import com.duong.models.UserModel;

public interface UserDao {
    UserModel getUser(String username);
    boolean register(String username, String password);
}