package com.ssm.service;


import java.util.List;

import com.ssm.model.UserStatistics;




public interface UserStatisticsService {
	
		void insert(UserStatistics attr);
	    
	    void deleteUserStatistics(String id);
	    
	    List<UserStatistics> queryAllUserStatistics();
	    
	    
}
