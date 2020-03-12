package com.example.demo.service;

import com.example.demo.service.model.PromoModel;

public interface PromoService {

    // 根据itemid获取即将或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
