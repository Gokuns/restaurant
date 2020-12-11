package com.yp.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Info {

    @Value("${server.port}")
    String port;

    @Value("${spring.application.name}")
    String name;

    @Value("${spring.h2.console.enabled}")
    String enabled;

    @Value("${logging.level.liquibase}")
    String dll_auto;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.jpa.show.sql}")
    String show_sql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    String format_sql;

    @Value("${logging.level.org.hibernate.type}")
    String hibernate_type;

    @Value("${spring.datasource.driverClassName}")
    String driverClassName;


    @Value("${spring.datasource.username}")
    String username;


    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
    String hbm2ddl_auto;


    @Value("${spring.datasource.initialization-mode}")
    String initialization_mode;


    public Info(){}

    public String getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getDll_auto() {
        return dll_auto;
    }

    public String getUrl() {
        return url;
    }

    public String getShow_sql() {
        return show_sql;
    }

    public String getFormat_sql() {
        return format_sql;
    }

    public String getHibernate_type() {
        return hibernate_type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHbm2ddl_auto() {
        return hbm2ddl_auto;
    }

    public String getInitialization_mode() {
        return initialization_mode;
    }
}
