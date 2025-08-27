package com.duong.services.impl;

import com.duong.models.UserModel;
import com.duong.dao.UserDao;
import com.duong.dao.impl.UserDaoImpl;
import com.duong.services.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public UserModel getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.getUser(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }
}
