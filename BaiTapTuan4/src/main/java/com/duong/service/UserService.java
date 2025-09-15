package com.duong.service;

import com.duong.entity.User;

public interface UserService {
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User login(String username, String password);
    boolean registerUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
