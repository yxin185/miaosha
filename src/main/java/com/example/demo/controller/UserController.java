package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.controller.viewobject.UserVO;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EmBusinessError;
import com.example.demo.response.CommonReturnType;
import com.example.demo.service.UserService;
import com.example.demo.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    // 用户登录接口
    @RequestMapping(value = "/login", method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 入参校验
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 用户登录服务，用来校验用户登录信息是否合法
        UserModel userModel = userService.validateLogin(telphone, this.EncodeByMD5(password));

        // 将登录凭证加入到用户登录成功的session内（该场景用户单点登录）
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }



    // 用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String optCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 验证手机号和对应的otpCode相符合
        // 先取到httpservlet中即session的otpcode，与用户传进来的otpcode做比较
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        // 包装了判空
        if (!StringUtils.equals(inSessionOtpCode, optCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }
        // 用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        // Integer转换成Byte，现将int值转换成String，再将String转成Byte
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        // 为了避免用户手机号多次注册，将数据库中手机号设置为唯一键
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncryptPassword(this.EncodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 加密字符串
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }


    // 用户获取OTP短信接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone") String telphone) {
        // 需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(89999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        // 将OTP验证码同对应用户的手机号关联,使用session的方式绑定手机号与OTPcode
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        // 将OTP验证码通过短信通道发送给用户,省略
//        log.info("telphone:{} & otpCode:{}", telphone, otpCode);
        System.out.println("telphone = " + telphone + " & otpCode = " + otpCode);
        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        // 调用Service层服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        // 若用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        // 将核心领域模型用户对象转换为可供UI使用的viewobject
        UserVO userVO = convertFromModel(userModel);
        // 返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            log.info("对象为空");
            return null;
        }
        UserVO userVO = new UserVO();
//        userVO.setAge(userModel.getAge());
//        userVO.setGender(userModel.getGender());
//        userVO.setId(userModel.getId());
//        userVO.setTelphone(userModel.getTelphone());
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
