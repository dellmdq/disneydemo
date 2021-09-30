package com.alkemy.disneydemo.config;

import com.alkemy.disneydemo.filter.CsrfLoggerFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class DisneyDemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.addFilterAfter(new CsrfLoggerFilter(),
                CsrfFilter.class);
    }
}