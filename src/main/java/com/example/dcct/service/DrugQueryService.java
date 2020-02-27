package com.example.dcct.service;


import com.example.dcct.entity.DrugEntity;

import java.util.Optional;

public interface DrugQueryService {

    Object getQueryInformation(String drugOne, String drugTwo);

    void saveQueryRecord(String drugOne, String drugTwo,long uid);
}
