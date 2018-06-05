package com.ssm.service;


import java.util.List;

import com.ssm.model.ConsumptionStatistics;





public interface ConsumptionStatisticsService {
	
		void insert(ConsumptionStatistics attr);
	    
	    void deleteConsumptionStatistics(String id);
	    
	    List<ConsumptionStatistics> queryAllConsumptionStatistics();
	    
	    
}
