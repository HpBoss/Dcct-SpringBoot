package com.example.dcct.controller;

import com.alibaba.fastjson.JSON;
import com.example.dcct.entity.CoverEntity;
import com.example.dcct.service.ReturnCoverService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ReturnCoverController {

    @Autowired
    ReturnCoverService returnCoverService;

    @GetMapping("/getAllCover")
    public Object getAllCover(){
        List<CoverEntity> entities = returnCoverService.getAllCover();
        if (entities != null) {
            return ResultUtils.success(JSON.toJSON(entities),"获取封面图信息成功");
        }
        return ResultUtils.error("获取封面图信息失败");
    }

}
