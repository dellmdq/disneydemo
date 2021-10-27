package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.dao.RoleDao;
import com.alkemy.disneydemo.dao.UserDao;

import com.alkemy.disneydemo.entity.Role;
import com.alkemy.disneydemo.entity.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public User save(User demoUser) {
		com.alkemy.disneydemo.entity.User user = new com.alkemy.disneydemo.entity.User();
		 // assign user details to the user object
		user.setUserName(demoUser.getUserName());
		user.setPassword(passwordEncoder.encode(demoUser.getPassword()));
		user.setFirstName(demoUser.getFirstName());
		user.setLastName(demoUser.getLastName());
		user.setEmail(demoUser.getEmail());
		user.setEnabled(false);
		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);

		 // save user in the database
		userDao.save(user);

		return user;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.alkemy.disneydemo.entity.User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public void sendVerificationEmail(User theUser) throws MessagingException, UnsupportedEncodingException {
		String subject = "Please verify your registration";
		String senderName = "DisneyDemo Team";
		String mailContent = "<p> Dear " + theUser.getFirstName() + "</p>";
		mailContent += "<p>Please click the link below to verify your registration.</p>";

		String verifyURL = "http://localhost:8080/auth/verify?code=" + theUser.getVerificationCode();

		mailContent += "<h3><a href=" + verifyURL + ">VERIFY</a></h3>";
		mailContent += "<p> Thank you <br> The DisneyDemo Team.<p>";


		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("alkemydisneydemo@gmail.com",senderName);
		helper.setTo(theUser.getEmail());
		helper.setSubject(subject);

		helper.setText(mailContent, true);

		javaMailSender.send(message);
	}

	@Override
	@Transactional
	public boolean verify(String verification_code) {
		User user = userDao.findByVerificationCode(verification_code);

		if (user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			System.out.println(user.getVerificationCode());
			userDao.update(user);
			return true;
		}
	}
}
