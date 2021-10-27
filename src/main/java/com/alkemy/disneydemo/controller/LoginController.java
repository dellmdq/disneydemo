package com.alkemy.disneydemo.controller;

import com.alkemy.disneydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String showMyLoginPage() {
		
		return "fancy-login";
		
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}

	@GetMapping("/verify")
	public String verifyUser(@RequestParam("code") String code) {
		if (userService.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}
	
}









