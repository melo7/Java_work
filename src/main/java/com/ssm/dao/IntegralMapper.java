package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Integral;

@Repository("integralMapper")
public interface IntegralMapper {
   
    
    void deleteIntegral(String id);
    
    List<Integral> queryAllIntegral();
    
    Integral updateIntegral(String id);
    
    void saveIntegral(Integral attr);
}