package com.ssm.service;


import java.util.List;

import com.ssm.model.AdderssAdmin;
import com.ssm.model.Advertising;



public interface AdvertisingService {
	
void insert(Advertising attr);
    
    void deleteAdvertising(String id);
    
    List<Advertising> queryAllAdvertising();
    
    Advertising updateAdvertising(String id);
    
    void saveAdvertising(Advertising attr);
}
