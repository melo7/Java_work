package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.RoadRescue;


@Repository("roadRescueMapper")
public interface RoadRescueMapper {
   
    void insert(RoadRescue attr);
    
    void deleteRoadRescue(String id);
    
    List<RoadRescue> queryAllRoadRescue();
}