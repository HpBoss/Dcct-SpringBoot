package com.example.dcct.service.Imp;

import com.example.dcct.dao.CoverDao;
import com.example.dcct.entity.CoverEntity;
import com.example.dcct.service.UploadImgService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
public class UploadImgServiceImp implements UploadImgService {

    @Autowired
    CoverDao coverDao;

    @Value("${image.rootPath}")
    private String ROOT_PATH;

    //图片URL接口
    @Value("${image.interface}")
    private String INTERFACE;

    @Value("${server.port}")
    //获取主机端口
    private String POST;

    @Override
    public boolean saveImages(List<MultipartFile> files,List<String> describe) throws IOException {
        List<String> newPath = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String imageFormat = originalFilename.substring(originalFilename.lastIndexOf("."));

            //创造1-1000的随机数
            Random random = new Random();
            Integer randomFileName = random.nextInt(1000);
            //将当前日期+随机数+图片格式，组合成新的图片名
            String fileName = removeDateLine() + randomFileName + imageFormat;

            File path = new File(ROOT_PATH + fileName);
            if (!path.getParentFile().exists()) {
                //假如文件不存在即重新创建新的文件已防止异常发生
                path.getParentFile().mkdirs();
            }
            file.transferTo(path);
            String filePathNew = INTERFACE + fileName;
            newPath.add(filePathNew);
        }
        return saveUploadImages(newPath,describe);
    }

    private boolean saveUploadImages(List<String> newPath,List<String> describe) {
        //本机IP
        String host = null;
        host = "47.102.206.167";

        //对于重复提交的情况，我们选择直接覆盖之前的信息
        if (coverDao.findById(1).isPresent()) {
            for (int i = 0; i < describe.size() ; i++) {
                Optional<CoverEntity> coverEntity = coverDao.findById(i+1);
                if (coverEntity.isEmpty()){//新上传的图片数量大于之前数据库中的数量
                    CoverEntity addCoverEntity = new CoverEntity();
                    addCoverEntity.setImageUrl("http://" + host + ":" + POST + newPath.get(i));
                    addCoverEntity.setDescribes(describe.get(i));
                    coverDao.save(addCoverEntity);
                    System.out.println("http://" + host + ":" + POST + newPath.get(i));
                }else {
                    coverEntity.get().setImageUrl("http://" + host + ":" + POST + newPath.get(i));
                    coverEntity.get().setDescribes(describe.get(i));
                    System.out.println("http://" + host + ":" + POST + newPath.get(i));
                    coverDao.save(coverEntity.get());
                }
            }
        }else {
            for (int i = 0; i < describe.size() ; i++) {
                CoverEntity coverEntity = new CoverEntity();
                coverEntity.setImageUrl("http://" + host + ":" + POST + newPath.get(i));
                //打印URL
                System.out.println("http://" + host + ":" + POST + newPath.get(i));
                coverEntity.setDescribes(describe.get(i));
                coverDao.save(coverEntity);
            }
        }
        return true;
    }

    public String removeDateLine(){
        //substring都搞不清楚了，哎。。。比如（0，2）2是没有取到的，0取到了
        String dateName = DateFormatUtils.format(new Date(),"MM-yyyy-dd");

        String month = dateName.substring(0,2);
        String year = dateName.substring(3,7);
        String day = dateName.substring(dateName.lastIndexOf("-")+1);

        return month + year + day;
    }
}
