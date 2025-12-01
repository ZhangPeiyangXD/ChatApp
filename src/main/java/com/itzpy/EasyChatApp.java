package com.itzpy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.itzpy")
@MapperScan("com.itzpy.mappers")
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class EasyChatApp {
    public static void main(String[] args) {
        SpringApplication.run(EasyChatApp.class, args);
    }
}
