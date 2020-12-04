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

    @Value("${spring.jpa.hibernate.dll-auto}")
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

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDll_auto() {
        return dll_auto;
    }

    public void setDll_auto(String dll_auto) {
        this.dll_auto = dll_auto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShow_sql() {
        return show_sql;
    }

    public void setShow_sql(String show_sql) {
        this.show_sql = show_sql;
    }

    public String getFormat_sql() {
        return format_sql;
    }

    public void setFormat_sql(String format_sql) {
        this.format_sql = format_sql;
    }

    public String getHibernate_type() {
        return hibernate_type;
    }

    public void setHibernate_type(String hibernate_type) {
        this.hibernate_type = hibernate_type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHbm2ddl_auto() {
        return hbm2ddl_auto;
    }

    public void setHbm2ddl_auto(String hbm2ddl_auto) {
        this.hbm2ddl_auto = hbm2ddl_auto;
    }

    public String getInitialization_mode() {
        return initialization_mode;
    }

    public void setInitialization_mode(String initialization_mode) {
        this.initialization_mode = initialization_mode;
    }
}
