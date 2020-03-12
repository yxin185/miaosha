package com.example.demo.controller;


import com.example.demo.controller.viewobject.ItemVO;
import com.example.demo.error.BusinessException;
import com.example.demo.response.CommonReturnType;
import com.example.demo.service.ItemService;
import com.example.demo.service.model.ItemModel;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Slf4j
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    // 创建商品的controller
    @RequestMapping(value = "/create", method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        // 封装service请求来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        itemModel.setDescription(description);
        ItemModel itemModelForReturn = itemService.createItem(itemModel);

        // 返回
        ItemVO itemVO = convertItemVOFromItemModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);

        ItemVO itemVO = convertItemVOFromItemModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    // 商品列表页面浏览
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> itemModels = itemService.listItem();
        // 使用Stream API将list内的ItemModel转换为ItemVolatile
        List<ItemVO> itemVOList = itemModels.stream().map(itemModel -> {
            ItemVO itemVO = this.convertItemVOFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);

    }



    private ItemVO convertItemVOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            // 将开始时间格式化一下
            itemVO.setPromoStartDate(itemModel.getPromoModel()
                    .getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));

            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            // 表示没有秒杀活动
            itemVO.setPromoStatus(0);
        }

        return itemVO;
    }
}
