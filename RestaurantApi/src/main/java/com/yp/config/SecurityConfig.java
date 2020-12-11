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

        http.authorizeRequests().antMatchers("/role/list").access("hasAnyRole('ADMIN',  'USER')");
        http.authorizeRequests().antMatchers("/role/{name}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/role/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/role/{name}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/role/{name}/delete").access("hasRole('ADMIN')");


        http.authorizeRequests().antMatchers("/user/list").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/user/{username}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/user/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{username}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{username}/delete").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/waiter/list").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/waiter/{id}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/waiter/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/{id}/delete").access("hasRole('ADMIN')");


        http.authorizeRequests().antMatchers("/order/checkout/add").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/order/list").access("hasAnyRole('ADMIN', 'USER')");


        http.authorizeRequests().antMatchers("/category/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/{id}/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/list").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/category/{id}/name").access("hasAnyRole('ADMIN', 'USER')");;
        http.authorizeRequests().antMatchers("/category/{id}").access("hasAnyRole('ADMIN', 'USER')");;

        http.authorizeRequests().antMatchers("/info/list").access("hasAnyRole('ADMIN', 'USER')");;

        http.authorizeRequests().antMatchers("/table_category/list").access("hasAnyRole('ADMIN', 'USER')");;
        http.authorizeRequests().antMatchers("/table_category/{id}").access("hasAnyRole('ADMIN', 'USER')");;
        http.authorizeRequests().antMatchers("/table_category/{id}/name").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table_category/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table_category/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table_category/{id}/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table_category/list/{id}").access("hasAnyRole('ADMIN', 'USER')");

        http.authorizeRequests().antMatchers("/table/list").access("hasAnyRole('ADMIN', 'USER')");;
        http.authorizeRequests().antMatchers("/table/{id}").access("hasAnyRole('ADMIN', 'USER')");;
        http.authorizeRequests().antMatchers("/table/add/{id}").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table/{id}/put/{tableCatId}").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/table/{id}/delete").access("hasRole('ADMIN')");


        http.httpBasic();
        http.cors();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
