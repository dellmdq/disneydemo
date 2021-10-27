package com.alkemy.disneydemo.service;


import com.alkemy.disneydemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public User save(User user);

	public void sendVerificationEmail(User theUser) throws MessagingException, UnsupportedEncodingException;

	public boolean verify(String verification_code);
}
