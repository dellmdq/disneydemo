package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
