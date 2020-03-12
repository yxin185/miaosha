package com.example.demo.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemModel {

    private Integer id;

    // 商品名字
    @NotBlank(message = "商品名称不能为空")

    private String title;
    // 价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格不能小于0")
    private BigDecimal price;

    // 库存
    @NotNull(message = "商品库存不能不填")
    private Integer stock;

    // 描述
    @NotBlank(message = "商品描述不能为空")
    private String description;

    // 销量
    private Integer sales;

    // 商品描述的图片url
    @NotBlank(message = "商品图片不能为空")
    private String imgUrl;

    // 使用聚合模型,如果promoModel不为空，则表示其还拥有还未结束的秒杀活动
    private PromoModel promoModel;
}
