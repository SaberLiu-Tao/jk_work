package com.lt.bankB.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/bankB")
public class BankController {



    /*@GetMapping("insert/{username}")
    public String insertMember(@PathVariable("username") String username){

        return userService.insertMember(username);
    }*/
}
