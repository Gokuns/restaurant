package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
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
}
