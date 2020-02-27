package com.example.dcct.service.Imp;


import com.example.dcct.dao.UserDao;
import com.example.dcct.entity.UserEntity;
import com.example.dcct.model.LoginState;
import com.example.dcct.model.User;
import com.example.dcct.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class UserLoginServiceImp implements UserLoginService {

    @Autowired
    UserDao userDao;


    /**
     * 返回一个User类型的JSON串
     * @param email 邮箱
     * @return JSON
     */
    @Override
    public Object getUserEntity(String email){
        Optional<UserEntity> userSrc = userDao.findOneByEmail(email);
        //不用判断userSrc是否存在，因为只有存在才会调用改方法
        UserEntity userEntity = userSrc.get();
        User user = new User(userEntity.getUid(),userEntity.getPassword(),userEntity.getNickname(),userEntity.getEmail());

        return user;
    }

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return 布尔值
     */
    @Override
    public int loginUser(String email,String password) {
        Optional<UserEntity> userSrc = userDao.findOneByEmail(email);
        if (userSrc.isPresent()) {
            if (userSrc.get().getState() == 0) {
                if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(userSrc.get().getPassword())) { // 校验密码是否一致
                    userSrc.get().setState(1);
                    userDao.save(userSrc.get());
                    return LoginState.STATE_SUCCESS; // 登录成功
                }else {
                    return LoginState.STATE_FAIL;
                }
            }else{
                return LoginState.STATE_EXIST;
            }
        }
        return LoginState.STATE_UNREGISTER;
    }
}
