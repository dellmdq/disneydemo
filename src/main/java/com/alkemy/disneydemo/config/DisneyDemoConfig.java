package com.alkemy.disneydemo.config;

import com.alkemy.disneydemo.filter.CsrfLoggerFilter;
import com.alkemy.disneydemo.utils.JsonPatchUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.monitorjbl.json.JsonViewSupportFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class DisneyDemoConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JsonPatchUtils jsonPatchUtils(){return new JsonPatchUtils();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/api/**","/","index","/css/*","js/*","/resources/**", "/registration","/auth/login","/characters/**").authenticated()
                    .antMatchers("/auth/register/**","auth/verify/**").permitAll()
                //.anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login/authenticateTheUser")
                .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .httpBasic()
                    .and()
                .exceptionHandling().accessDeniedPage("/auth/access-denied")
                    .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                .logout().permitAll();

        http.addFilterAfter(new CsrfLoggerFilter(),
                CsrfFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }


}
