package com.example.dcct.controller;

import com.example.dcct.service.UploadImgService;
import com.example.dcct.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class UploadController {
    @Autowired
    UploadImgService uploadImgService;

    @PostMapping("/img/upload")
    public Object login(@RequestParam(value = "files") List<MultipartFile> files,
                        @RequestParam(value = "describe") List<String> describe) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                return ResultUtils.error("上传的图片存在空");
            }
        }
        for (String s : describe) {
            if (s.equals("")) {
                return ResultUtils.error("描述文字存在空");
            }
        }
        if (describe.size() != files.size()){
            return ResultUtils.error("上传信息不对称");
        }
        if (uploadImgService.saveImages(files,describe)) {
            return ResultUtils.success("图片上传成功");
        }else {
            return ResultUtils.error("图片存储失败");
        }
    }
}
