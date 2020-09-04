package com.changhong;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.changhong.repository")
public class FindApplication {
    public static void main(String[] args) {
        SpringApplication.run(FindApplication.class,args);
    }
}
