package com.example.dcct.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadImgService {
    boolean saveImages(List<MultipartFile> files,List<String> describe) throws IOException;
}
