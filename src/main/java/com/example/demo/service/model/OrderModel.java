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

    // 若非空，则是以秒杀商品方式下单
    private Integer promoId;

    // 购买商品的单价,若promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;

    // 购买数量
    private Integer amount;

    // 购买总金额，若promoId非空，则表示秒杀商品价格
    private BigDecimal orderPrice;

}
