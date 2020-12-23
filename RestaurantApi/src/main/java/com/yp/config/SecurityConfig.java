package com.yp.config;


import com.yp.auth.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private static final String[] AUTH_LIST = {
            // -- swagger ui
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.authorizeRequests().antMatchers("swagger-ui.html/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/product/list").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/view/{category}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/categories").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/{id}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/product/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/{id}/delete").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/role/list").access("hasAnyRole('ADMIN',  'USER')");
        http.authorizeRequests().antMatchers("/role/{id}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/role/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/role/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/role/{id}/delete").access("hasRole('ADMIN')");


        http.authorizeRequests().antMatchers("/user/list").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/user/{id}").access("hasAnyRole('ADMIN', 'USER')");
        http.authorizeRequests().antMatchers("/user/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{id}/put").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/{id}/delete").access("hasRole('ADMIN')");

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
        auth.authenticationProvider(authenticationProvider());
    }
}
