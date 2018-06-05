package com.ssm.service;


import java.util.List;

import com.ssm.model.Insurance;





public interface InsuranceService {
	
		void insert(Insurance attr);
	    
	    void deleteInsurance(String id);
	    
	    List<Insurance> queryAllInsurance();
	    
	    
}
