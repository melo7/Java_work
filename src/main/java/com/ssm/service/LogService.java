package com.ssm.service;


import java.util.List;

import com.ssm.model.Log;


public interface LogService {
	
		void insert(Log attr);
	    
	    void deleteLog(String id);
	    
	    List<Log> queryAllLog();
	    
	    Log updateLog(String id);
	    
	    void saveLog(Log attr);
}
