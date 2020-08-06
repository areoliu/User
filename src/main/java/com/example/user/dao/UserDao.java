package com.example.user.dao;

import com.example.user.entity.Role;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from springcloud_user where username=#{username}")
    public User selectUser(String username);


    @Select("select r.id, r.name from springcloud_user_role ur, springcloud_role r where ur.role_id = r.id and ur.user_id = #{id}")
    public Role selectUserRole(int id);
}
