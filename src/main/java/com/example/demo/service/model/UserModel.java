package com.example.demo.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserModel {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别不能不填")
    private Byte gender;
    @NotNull(message = "年龄不能不填")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄不能大于150")
    private Integer age;
    @NotBlank(message = "手机号不能不填")
    private String telphone;
    private String registerMode;
    private String thirdPartyMode;
    @NotBlank(message = "密码不能不填")
    private String encryptPassword;

}
