package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.ServicePackage;
@Repository("servicePackageMapper")
public interface ServicePackageMapper {
   
    void insert(ServicePackage attr);
    
    void deleteServicePackage(String id);
    
    List<ServicePackage> queryAllServicePackage();
    
    ServicePackage updateServicePackage(String id);
    
    void saveServicePackage(ServicePackage attr);
}