package com.example.dcct.service.Imp;

import com.example.dcct.dao.UserDao;
import com.example.dcct.entity.UserEntity;
import com.example.dcct.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class UserRegisterServiceImp implements UserRegisterService {
    @Autowired
    UserDao userDao;

    /**
     * 查看改邮箱账号是否已经被注册
     * @param email 注册邮箱
     * @return 布尔值
     */
    @Override
    public boolean isExistAccount(String email){
        Optional<UserEntity> userSrc = userDao.findOneByEmail(email);
        if (userSrc.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 注册
     * @param nickname 用户名
     * @param password 密码
     * @param email 邮箱
     */
    @Override
    public void addUser(String nickname,String password,String email) {
        UserEntity userEntity = new UserEntity();
//        Optional<UserEntity> userSrc = userDao.findOneByEmail(email);

        userEntity.setEmail(email);
        userEntity.setNickname(nickname);
        userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));//密码加密
        userDao.save(userEntity);

    }
}
