package com.example.dcct.service.Imp;

import com.example.dcct.dao.UserDao;
import com.example.dcct.entity.UserEntity;
import com.example.dcct.service.UserSignOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignOutServiceImp implements UserSignOutService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean signOut(long uid) {
        UserEntity userEntity = userDao.findByUid(uid).get();
        userEntity.setState(0);
        userDao.save(userEntity);
        return true;
    }
}
