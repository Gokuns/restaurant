package com.yp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/product/list").access("hasAnyRole('ADMIN',  'USER')");
        http.authorizeRequests().antMatchers("/product/view/{category}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/categories").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/{id}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/{id}/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{username}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/user/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{username}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{username}/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/order/**").access("hasAnyRole('ADMIN', 'USER')");;
        http.httpBasic();
        http.cors();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
