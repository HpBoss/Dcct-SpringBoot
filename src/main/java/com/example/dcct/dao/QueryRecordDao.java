package com.example.dcct.dao;


import com.example.dcct.entity.CoverEntity;
import com.example.dcct.entity.QueryRecordEntity;
import com.example.dcct.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface QueryRecordDao extends PagingAndSortingRepository<QueryRecordEntity, Long> {

    List<Optional<QueryRecordEntity>> findByUserEntity(UserEntity userEntity);
}
