package com.example.demo.controller.viewobject;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemVO {

    private Integer id;
    // 商品名字
    private String title;
    // 价格
    private BigDecimal price;
    // 库存
    private Integer stock;
    // 描述
    private String description;
    // 销量
    private Integer sales;
    // 商品描述的图片url
    private String imgUrl;

    // 商品知否在秒杀活动中中，以及对应的状态，0：没有秒杀活动，1：带开始，2：秒杀正在进行
    private Integer promoStatus;

    // 秒杀活动价格
    private BigDecimal promoPrice;

    // 秒杀活动ID
    private Integer promoId;

    // 秒杀活动开始时间
    private String promoStartDate;
}
