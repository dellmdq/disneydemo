package com.alkemy.disneydemo.service;


import com.alkemy.disneydemo.entity.User;
import com.alkemy.disneydemo.user.DemoUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(DemoUser demoUser);
}
