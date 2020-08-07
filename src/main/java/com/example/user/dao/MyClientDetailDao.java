package com.example.user.dao;


import com.example.user.entity.MyClientDetail;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MyClientDetailDao {

    @Select("select * from oauth_client_details where client_id=#{clientId}")
    public MyClientDetail loadClientByClientId(String clientId);
}
