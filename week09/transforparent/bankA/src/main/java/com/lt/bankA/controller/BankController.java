package com.lt.bankA.controller;

import com.lt.bankA.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@RequestMapping("/bankA")
public class BankController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("getMember/{username}")
    public String getMember(@PathVariable("username") String username){

        int userId=1;
        int type1=1;
        int type2=1;
        String amount="7";
        int toUserId=2;

        boolean Iftransfor = this.bankAccountService.transfor(userId, type1, type2, amount, toUserId,0L);
        return Iftransfor+"";
    }

    /*@GetMapping("insert/{username}")
    public String insertMember(@PathVariable("username") String username){

        return userService.insertMember(username);
    }*/
}
