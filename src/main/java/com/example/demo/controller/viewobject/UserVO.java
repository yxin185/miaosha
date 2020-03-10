package com.example.demo.controller.viewobject;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    // 用来向前端展示
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;

}
