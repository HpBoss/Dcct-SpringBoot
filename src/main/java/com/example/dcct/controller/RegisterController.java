package com.example.dcct.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dcct.service.Imp.UserRegisterServiceImp;
import com.example.dcct.service.UserRegisterService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class RegisterController {
    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/register")
    public Object register(@RequestBody JSONObject jsonObject) {

        String nickname = jsonObject.get("nickname").toString();
        String password = jsonObject.get("password").toString();
        String email = jsonObject.get("email").toString();

        if (userRegisterService.isExistAccount(email)) {
            //改邮箱账号已注册
            return ResultUtils.error("改邮箱已被注册！！！");
        }else {
            userRegisterService.addUser(nickname,password,email);
            return ResultUtils.success("注册成功");
        }

    }
}
