package com.example.dcct.dao;


import com.example.dcct.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserDao extends PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> findByUid(Long uid);

    Optional<UserEntity> findOneByEmail(String email);
}
