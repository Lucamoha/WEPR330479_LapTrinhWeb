package com.duong.services;

import com.duong.models.UserModel;

public interface UserService {
    UserModel getUser(String username);
    UserModel login(String username, String password);
    boolean checkExistEmail(String email);
    boolean register(String username, String email, String password);
    boolean resetPassword(String email, String password);
}