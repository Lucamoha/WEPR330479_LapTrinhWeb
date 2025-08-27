package com.duong.services;

import com.duong.models.UserModel;

public interface UserService {
    UserModel getUser(String username);
    UserModel login(String username, String password);
}
