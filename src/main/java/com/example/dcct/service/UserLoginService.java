package com.example.dcct.service;

public interface UserLoginService {
    default Object getUserEntity(String email) {
        return null;
    }

    int loginUser(String email,String password);
}
