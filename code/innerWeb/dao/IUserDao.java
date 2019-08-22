package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface IUserDao {

    //根据用户名和密码查询用户对象
    @Select("select * from user where username=#{username} and password=#{password};")
    User findUser(@Param("username") String name, @Param("password") String password);

    //    判断用户数据是否存在
    @Select("select * from user where username=#{username};")
    User isExist(@Param("username") String name);

//    存储注册用户
    @Insert("INSERT INTO user (username,password) values (#{username},#{password});")
    void storeUser(@Param("username") String name, @Param("password") String password);

}
