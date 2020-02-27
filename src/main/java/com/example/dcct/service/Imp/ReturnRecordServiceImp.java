package com.example.dcct.service.Imp;

import com.example.dcct.dao.CoverDao;
import com.example.dcct.dao.QueryRecordDao;
import com.example.dcct.dao.UserDao;
import com.example.dcct.entity.CoverEntity;
import com.example.dcct.entity.QueryRecordEntity;
import com.example.dcct.entity.UserEntity;
import com.example.dcct.model.Record;
import com.example.dcct.service.ReturnCoverService;
import com.example.dcct.service.ReturnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnRecordServiceImp implements ReturnRecordService {

    @Autowired
    UserDao userDao;

    @Autowired
    QueryRecordDao recordDao;

    @Override
    public Object getRecordOfId(long uid) {
        UserEntity userEntity = userDao.findByUid(uid).get();
        List<Record> recordList = new ArrayList<>();
        for (Optional<QueryRecordEntity> queryRecordEntity : recordDao.findByUserEntity(userEntity)) {
            Record record = new Record(queryRecordEntity.get().getDoubleDrug(),queryRecordEntity.get().getCreateTime());
            recordList.add(record);
        }
        return recordList;
    }
}
