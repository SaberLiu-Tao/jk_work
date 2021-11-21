package com.lt.bankA;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author itslm
 */
@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.lt.bankA.mapper")
public class BankAApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAApplication.class, args);
    }
}
