package com.example.user.service;

import com.example.user.entity.AuthCode;
import org.apache.ibatis.annotations.Delete;

public interface AuthCodeService {

    public void insert(AuthCode authCode);

    public boolean delete(String code);

    public AuthCode select(String code);

}
