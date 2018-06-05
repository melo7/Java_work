package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.ConsumptionStatistics;





@Repository("consumptionStatisticsMapper")
public interface ConsumptionStatisticsMapper {
   
    void insert(ConsumptionStatistics attr);
    
    void deleteConsumptionStatistics(String id);
    
    List<ConsumptionStatistics> queryAllConsumptionStatistics();
    
   
}