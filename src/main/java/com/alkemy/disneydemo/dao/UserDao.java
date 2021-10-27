package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.entity.User;

import java.util.List;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);

    public User findByVerificationCode(String verification_code);

    public void update(User user);
}
