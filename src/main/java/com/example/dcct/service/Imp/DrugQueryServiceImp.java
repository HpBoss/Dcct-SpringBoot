package com.example.dcct.service.Imp;

import com.example.dcct.dao.DrugDao;
import com.example.dcct.dao.DrugNameDao;
import com.example.dcct.dao.QueryRecordDao;
import com.example.dcct.dao.UserDao;
import com.example.dcct.entity.DrugEntity;
import com.example.dcct.entity.DrugNameEntity;
import com.example.dcct.entity.QueryRecordEntity;
import com.example.dcct.entity.UserEntity;
import com.example.dcct.service.DrugQueryService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DrugQueryServiceImp implements DrugQueryService {

    @Autowired
    DrugDao drugDao;

    @Autowired
    DrugNameDao drugNameDao;

    @Autowired
    UserDao userDao;

    @Override
    public Object getQueryInformation(String drugOne, String drugTwo) {
        Optional<DrugNameEntity> drugNameEntity = drugNameDao.findByDrugOneAndDrugTwo(drugOne, drugTwo);
        if (drugNameEntity.isPresent()) {
            return drugDao.findByDrugNameEntity(drugNameEntity).get();
        }
        return null;
    }

    @Override
    public void saveQueryRecord(String drugOne, String drugTwo, long uid) {
        QueryRecordEntity queryRecordEntity = new QueryRecordEntity();
        queryRecordEntity.setDoubleDrug(drugOne + "、" + drugTwo);
        queryRecordEntity.setCreateTime(DateFormatUtils.format(new Date(),"yyyy.MM.dd HH:mm"));

        UserEntity userEntity = userDao.findByUid(uid).get();

        List<QueryRecordEntity> recordEntityList = new ArrayList<>();
        recordEntityList.add(queryRecordEntity);
        userEntity.setRecordEntityList(recordEntityList);

        queryRecordEntity.setUserEntity(userEntity);
        userDao.save(userEntity);

    }
}
