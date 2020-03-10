package com.example.demo.validator;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
public class ValidationResult {
    // 校验结果是否有错
    private boolean hasErrors = false;

    // 存放信息的Map
    private Map<String, String> errorMsgMap = new HashMap<>();

    // 实现通用的通过格式化字符串信息获取错误结果的msg方法
    public String getErrMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}
