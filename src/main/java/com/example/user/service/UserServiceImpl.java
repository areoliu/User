package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User selectUser(String username) {
        return userDao.selectUser(username);
    }

    @Override
    public Role selectUserRole(int id) {
        return userDao.selectUserRole(id);
    }
}
