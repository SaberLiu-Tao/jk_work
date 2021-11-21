package com.lt.bankB.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lt.bankB.entity.AccountInfo;
import com.lt.bankB.entity.BankFreeze;
import com.lt.bankB.mapper.AccountInfoMapper;
import com.lt.bankB.mapper.BankFreezeMapper;
import lombok.extern.slf4j.Slf4j;
import lt.TransforBservice;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService
@Slf4j
public class BankBServiceImpl implements TransforBservice {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private BankFreezeMapper bankFreezeMapper;


    @Override
    @Transactional
    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean transfor(int userId, int type1, int type2, String amount, int toUserId, Long id) {
        LambdaQueryWrapper<AccountInfo> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AccountInfo::getUserId,toUserId);
        AccountInfo accountInfo = new AccountInfo();
        AccountInfo accountInfo1 = accountInfoMapper.selectOne(wrapper1);
        accountInfo.setAcountTypeA(accountInfo1.getAcountTypeA()+Integer.valueOf(amount));
        accountInfo.setAcountTypeB(accountInfo1.getAcountTypeB()-(Integer.valueOf(amount))/7);
        try {
            accountInfoMapper.update(accountInfo,wrapper1);
            BankFreeze bankFreeze = new BankFreeze();
            bankFreeze.setAccountTypeBefore(2);
            bankFreeze.setAccountTypeAfter(1);
            bankFreeze.setAmount(amount);
            bankFreeze.setUserId(userId);
            bankFreeze.setIsValidate(0);
            bankFreezeMapper.insert(bankFreeze);
            id = bankFreeze.getId();
        } catch (Exception e) {
            throw new RuntimeException("bank1 try 扣减金额失败,userId:{}"+userId);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(int userId, int type1, int type2, String amount, int toUserId,Long id) {
        log.info("bank2 confirm begin amount is {} ！！！", userId, toUserId, amount);
        //将转账状态改成1
        BankFreeze bankFreeze = new BankFreeze();
        bankFreeze.setId(id);
        bankFreeze.setIsValidate(1);
        log.info("bank2成功了");
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(int userId, int type1, int type2, String amount, int toUserId,Long id) {
        log.info("bank2 cancel begin amount is {} ！！！", userId, toUserId, amount);
        //将扣除的金额返回给用户
        LambdaQueryWrapper<AccountInfo> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AccountInfo::getUserId,userId);
        AccountInfo accountInfo = new AccountInfo();
        AccountInfo accountInfo1 = accountInfoMapper.selectOne(wrapper1);
        accountInfo.setAcountTypeA(accountInfo1.getAcountTypeA()-7*Integer.valueOf(amount));
        accountInfo.setAcountTypeB(accountInfo1.getAcountTypeB()+7*Integer.valueOf(amount));
        accountInfoMapper.update(accountInfo,wrapper1);
        return true;
    }
}
