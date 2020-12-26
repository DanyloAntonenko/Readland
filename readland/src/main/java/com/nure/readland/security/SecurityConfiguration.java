package com.nure.readland.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/admin").hasRole("admin").and()
                .authorizeRequests().antMatchers("/user").hasRole("user").and()
                .authorizeRequests().antMatchers("/lib").hasRole("lib").and()
                .formLogin().loginPage("/login").permitAll().successHandler(successHandler).and()
                .csrf().disable();
    }
}
