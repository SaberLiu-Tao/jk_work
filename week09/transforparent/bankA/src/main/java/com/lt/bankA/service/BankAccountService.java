package com.lt.bankA.service;

public interface BankAccountService {

    boolean transfor(int userId, int type1, int type2, String amount, int toUserId, Long id);
}
