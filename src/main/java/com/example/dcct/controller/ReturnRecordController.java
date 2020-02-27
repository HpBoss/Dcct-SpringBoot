package com.example.dcct.controller;

import com.alibaba.fastjson.JSON;
import com.example.dcct.entity.CoverEntity;
import com.example.dcct.entity.QueryRecordEntity;
import com.example.dcct.model.Record;
import com.example.dcct.service.ReturnCoverService;
import com.example.dcct.service.ReturnRecordService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/record")
@Slf4j
public class ReturnRecordController {

    @Autowired
    ReturnRecordService returnRecordService;

    @GetMapping("/{uid}")
    public Object getAllRecord(@PathVariable String uid){
        List<Record> recordList = (List<Record>) returnRecordService.getRecordOfId(Long.parseLong(uid));
        if (recordList != null && !recordList.isEmpty()) {
            return ResultUtils.success(recordList);
        }
        return ResultUtils.error();
    }

}
