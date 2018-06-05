package com.ssm.service;


import java.util.List;

import com.ssm.model.PushMessage;



public interface PushMessageService {
	
		void insert(PushMessage attr);
	    
	    void deletePushMessage(String id);
	    
	    List<PushMessage> queryAllPushMessage();
	    
	    PushMessage updatePushMessage(String id);
	    
	    void savePushMessage(PushMessage attr);
}
