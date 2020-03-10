package com.example.demo.service;


import com.example.demo.error.BusinessException;
import com.example.demo.service.model.UserModel;

public interface UserService {

    // 通过用户ID获取用户对象
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param telphone：用户注册手机
     * @param encryptPassword：用户加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String telphone, String encryptPassword) throws BusinessException;
}
