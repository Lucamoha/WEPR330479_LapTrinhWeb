package com.duong.dao;

import com.duong.entity.User;

public interface UserDao {
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User login(String username, String password);
    boolean registerUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
