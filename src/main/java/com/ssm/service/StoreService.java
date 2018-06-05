package com.ssm.service;


import java.util.List;

import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;
import com.ssm.model.Store;

public interface StoreService {
	
		void insert(Store attr);
	    
	    void deleteStore(String id);
	    
	    List<Store> queryAllStore();
	    
	    Store updateStore(String id);
	    
	    void saveMemberStore(Store attr);
}
