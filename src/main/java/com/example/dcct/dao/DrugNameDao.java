package com.example.dcct.dao;


import com.example.dcct.entity.DrugNameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DrugNameDao extends PagingAndSortingRepository<DrugNameEntity, Long> {

    Optional<DrugNameEntity> findByDrugOneAndDrugTwo(String drugOne,String drugTwo);

}
