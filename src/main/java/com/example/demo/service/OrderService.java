package com.example.demo.service;

import com.example.demo.error.BusinessException;
import com.example.demo.service.model.OrderModel;

public interface OrderService {

    // 推荐 1.通过url传过来秒杀活动id，然后下单接口内校验对应Id是否属于对应商品且活动已开始
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;

    // 2.直接在下单接口内判断对应的商品是否存在秒杀活动，如果存在进行中的则以秒杀价格下单

}
