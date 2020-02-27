package com.example.dcct.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.dcct.model.LoginState;
import com.example.dcct.service.UserLoginService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class LoginController {

    @Autowired
    UserLoginService userLoginService;

    @PostMapping(value = "/login")
    public Object login(@RequestBody JSONObject jsonObject) {

        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        int state = userLoginService.loginUser(email,password);
        switch (state){
            case LoginState.STATE_SUCCESS:
                return ResultUtils.success(userLoginService.getUserEntity(email),"登录成功");
            case LoginState.STATE_EXIST:
                return ResultUtils.error("该用户已登陆");
            case LoginState.STATE_UNREGISTER:
                return ResultUtils.error("该邮箱还未注册");
            case LoginState.STATE_FAIL:
                return ResultUtils.error("输入密码错误");
            default:
                return null;
        }
    }
}
