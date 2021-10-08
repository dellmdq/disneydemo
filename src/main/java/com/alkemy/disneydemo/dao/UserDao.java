package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
