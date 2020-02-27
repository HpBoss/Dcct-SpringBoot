package com.example.dcct.dao;


import com.example.dcct.entity.CoverEntity;
import lombok.experimental.Delegate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CoverDao extends PagingAndSortingRepository<CoverEntity, Integer> {
    @Override
    void deleteAll();

    @Override
    Iterable<CoverEntity> findAll();

    @Override
    Optional<CoverEntity> findById(Integer integer);

    @Query(value = "select c.imageUrl from CoverEntity c")
    List<String> getAllImageUrl();



}
