package com.yp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        for(String arg:args){
            System.out.println(arg);
        }
        SpringApplication.run(Main.class);
        System.out.println("asdasd");
    }
}
