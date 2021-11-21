package com.lt.bankB.entity;

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
@TableName("t_account_info")
@Slf4j
public class AccountInfo {
    /**
     * 主键id
     */
    @TableId(type= IdType.ID_WORKER)
    private Long id;

    /**
     * 名称
     */
    private int  userId;


    /**
     *
     */
    private String  userName;

    private int acountTypeA;
    private int acountTypeB;
    private LocalDateTime createTime;

}
