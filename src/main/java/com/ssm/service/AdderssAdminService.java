package com.ssm.service;


import java.util.List;

import com.ssm.model.AdderssAdmin;



public interface AdderssAdminService {
	
	void insert(AdderssAdmin attr);
    
    void deleteAdderssAdmin(String id);
    
    List<AdderssAdmin> queryAllAdderssAdmin();
	
    public AdderssAdmin updateAdderssAdmin(String id);
    
    void saveAdderssAdmin(AdderssAdmin attr);
}
