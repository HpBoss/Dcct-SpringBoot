package com.example.dcct.dao;


import com.example.dcct.entity.CoverEntity;
import com.example.dcct.entity.DrugEntity;
import com.example.dcct.entity.DrugNameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface DrugDao extends PagingAndSortingRepository<DrugEntity, Long> {

    Optional<DrugEntity> findByDrugNameEntity(Optional<DrugNameEntity> drugNameEntity);

}
