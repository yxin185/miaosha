package com.example.demo.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonReturnType {
    // 表明对应请求的返回处理结果"success:和fail
    private String status;
    // 若status = success，则data返回前端需要的json数据
    // 反之，则data内使用通用的错误码格式
    private Object data;

    // 定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

}
