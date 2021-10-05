package com.alkemy.disneydemo.auth.dao;

import com.alkemy.disneydemo.auth.model.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAll();

    public Role get(int theId);

    public void save(Role theRole);//add set id to zero

    public void delete(int theId);
}
