package com.derek.demo.SelfBatis.Dao;

import com.derek.demo.SelfBatis.Entity.User;

import java.util.List;

public interface UserMapper {

    public abstract List<User> findAllUsers();
}
