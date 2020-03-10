package com.example.demo.controller.viewobject;


import lombok.Getter;
import lombok.Setter;

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
}
