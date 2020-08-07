package com.example.user.service;

import com.example.user.dao.MyClientDetailDao;
import com.example.user.entity.MyClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyClientDetailServiceImpl implements MyClientDetailService{

    @Autowired
    MyClientDetailDao myClientDetailDao;

    @Override
    public MyClientDetail loadClientByClientId(String clientId) {
        return myClientDetailDao.loadClientByClientId(clientId);
    }
}
