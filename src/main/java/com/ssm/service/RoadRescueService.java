package com.ssm.service;


import java.util.List;

import com.ssm.model.RoadRescue;




public interface RoadRescueService {
	
	void insert(RoadRescue attr);
    
    void deleteRoadRescue(String id);
    
    List<RoadRescue> queryAllRoadRescue();
}
