package com.example.user.service;

import com.example.user.entity.AuthCode;
import com.example.user.entity.Role;
import com.example.user.entity.User;

public interface AuthCodeService {

    public void insert(String code,byte[] authentication);

    public boolean delete(String code);

    public AuthCode select(String code);
}
