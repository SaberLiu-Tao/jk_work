package com.lt.bankA.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_bank_freeze")
@Slf4j
public class BankFreeze {
    /**
     * 主键id
     */
    @TableId(type=IdType.ID_WORKER)
    private Long id;
    private int userId;
    private int accountTypeBefore;
    private int accountTypeAfter;
    private String amount;
    private int  isValidate;
    private LocalDateTime updateTime;
}
