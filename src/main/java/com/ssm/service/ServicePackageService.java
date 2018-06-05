package com.ssm.service;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.CarServiceModel;
import com.ssm.model.ServicePackage;


public interface ServicePackageService {
	
	void insert(ServicePackage attr);
    
    void deleteServicePackage(String id);
    
    List<ServicePackage> queryAllServicePackage();
	
    public ServicePackage updateServicePackage(String id);
    
    void saveServicePackage(ServicePackage attr);
}
