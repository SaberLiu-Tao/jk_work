package com.lt.bankB;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.lt.bankB.mapper")
public class BankBApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankBApplication.class, args);
    }
}
