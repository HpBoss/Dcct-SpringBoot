package com.example.dcct.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dcct.service.UserSignOutService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class SignOutController {

    @Autowired
    UserSignOutService userSignOutService;

    @PostMapping(value = "/sign_out/{uid}")
    public Object signOut(@PathVariable String uid) {
        userSignOutService.signOut(Long.parseLong(uid));
        return ResultUtils.success("退出登录成功");
    }
}
