package com.example.dcct.service.Imp;

import com.example.dcct.dao.CoverDao;
import com.example.dcct.entity.CoverEntity;
import com.example.dcct.service.ReturnCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnCoverServiceImp implements ReturnCoverService {

    @Autowired
    CoverDao coverDao;

    @Override
    public List<CoverEntity> getAllCover() {
        return (List<CoverEntity>) coverDao.findAll();
    }
}
