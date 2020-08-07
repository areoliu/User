package com.example.user.service;


import com.example.user.dao.AuthCodeDao;
import com.example.user.entity.AuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    @Autowired
    AuthCodeDao authCodeDao;

    @Override
    public void insert(AuthCode authCode) {
        authCodeDao.insert(authCode);
    }

    @Override
    public boolean delete(String code) {
        return authCodeDao.delete(code);
    }

    @Override
    public AuthCode select(String code) {
        return authCodeDao.select(code);
    }
}
