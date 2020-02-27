package com.example.dcct.service;

public interface UserRegisterService {
    default boolean isExistAccount(String email) {
        return false;
    }

    void addUser(String nickname, String password, String email);
}
