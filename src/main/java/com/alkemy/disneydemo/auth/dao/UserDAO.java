package com.alkemy.disneydemo.auth.dao;

import com.alkemy.disneydemo.auth.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAll();

    public User get(int theId);

    public void save(User theUser);//add set id to zero

    public void delete(int theId);

}
