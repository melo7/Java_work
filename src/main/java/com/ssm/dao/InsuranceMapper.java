package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Insurance;





@Repository("insuranceMapper")
public interface InsuranceMapper {
   
    void insert(Insurance attr);
    
    void deleteInsurance(String id);
    
    List<Insurance> queryAllInsurance();
}