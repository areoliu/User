package com.example.user.dao;

import com.example.user.entity.AuthCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeDao {

    @Insert("insert into oauth_code(code,authentication)values(#{code},#{authentication})")
    public void insert(String code,byte[] authentication);

    @Delete("delete from oauth_code where code=#{code}")
    public boolean delete(String code);

    @Select("select *  from oauth_code where code=#{code}")
    public AuthCode select(String code);

}
