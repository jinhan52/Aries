package com.kim.security.aries;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.kim.security.aries.mapper"})
public class AriesApplication {
    public static void main(String[] args) {
        SpringApplication.run(AriesApplication.class, args);
    }

}
