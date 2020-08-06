package com.example.user.service;

import com.example.user.entity.Role;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserService {

    public User selectUser(String username);

    public Role selectUserRole(int id);
}
