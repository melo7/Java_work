package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Advertising;



@Repository("advertisingMapper")
public interface AdvertisingMapper {
   
    void insert(Advertising attr);
    
    void deleteAdvertising(String id);
    
    List<Advertising> queryAllAdvertising();
    
    Advertising updateAdvertising(String id);
    
    void saveAdvertising(Advertising attr);
}