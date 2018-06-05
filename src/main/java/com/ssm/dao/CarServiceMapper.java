package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;

@Repository("carServiceMapper")
public interface CarServiceMapper {
   
    void insert(CarServiceModel attr);
    
    void deleteCarService(String id);
    
    List<CarServiceModel> queryAllCarService();
    
    CarServiceModel updateCarServiceModel(String id);
    
    void saveMemberCarServiceModel(CarServiceModel attr);
}