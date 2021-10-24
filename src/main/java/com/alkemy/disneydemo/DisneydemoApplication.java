package com.alkemy.disneydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DisneydemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DisneydemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DisneydemoApplication.class, args);

	}





}


/**TODO
 * Change Movies Date from String to local date
 * Generify the applyjsonpatch method.
 * **/