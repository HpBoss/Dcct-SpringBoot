package com.example.dcct.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.dcct.entity.DrugEntity;
import com.example.dcct.service.DrugQueryService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
public class DrugQueryController {

    @Autowired
    DrugQueryService drugQueryService;

    @PostMapping("/query")
    public Object query(@RequestBody JSONObject jsonObject) {

        String drugOne = jsonObject.getString("drugOne");
        String drugTwo = jsonObject.getString("drugTwo");
        long uid = jsonObject.getLong("uid");

        Object object = drugQueryService.getQueryInformation(drugOne,drugTwo);
        if ( object != null) {
           DrugEntity drugEntity1 = (DrugEntity) object;
            //执行保存查询记录的操作
            drugQueryService.saveQueryRecord(drugOne,drugTwo,uid);
            //接着查询另一个
            DrugEntity drugEntity2 = (DrugEntity) drugQueryService.getQueryInformation(drugTwo,drugOne);
            List<DrugEntity> drugEntityList = new ArrayList<>();
            drugEntityList.add(drugEntity1);
            drugEntityList.add(drugEntity2);
            return ResultUtils.success(drugEntityList,"查询成功");
        }
        return null;
    }

}
