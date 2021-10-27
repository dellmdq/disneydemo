package com.alkemy.disneydemo.controller;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.alkemy.disneydemo.entity.User;
import com.alkemy.disneydemo.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/register")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("user", new User());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("demoUser") User theDemoUser,
				BindingResult theBindingResult, 
				Model theModel) throws MessagingException, UnsupportedEncodingException {
		
		String userName = theDemoUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("demoUser", new User());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }



        // create user account        						
        theDemoUser = userService.save(theDemoUser);
        
        logger.info("Successfully created user: " + userName);


		userService.sendVerificationEmail(theDemoUser);
        
        return "registration-confirmation";		
	}


}
