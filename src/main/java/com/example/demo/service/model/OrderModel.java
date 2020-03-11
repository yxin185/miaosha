package com.example.demo.service.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户下单的交易模型
 */
@Getter
@Setter
public class OrderModel {

    // 交易号2020031112345
    private String id;

    // 用户Id
    private Integer userId;

    // 购买的商品id
    private Integer itemId;

    // 购买商品的单价
    private BigDecimal itemPrice;

    // 购买数量
    private Integer amount;

    // 购买总金额
    private BigDecimal orderPrice;

}
