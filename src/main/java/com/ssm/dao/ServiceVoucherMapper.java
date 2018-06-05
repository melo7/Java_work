package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.ServiceVoucher;

@Repository("serviceVoucherMapper")
public interface ServiceVoucherMapper {
   
    void insert(ServiceVoucher attr);
    
    void deleteServiceVoucher(String id);
    
    List<ServiceVoucher> queryAllServiceVoucher();
    
    ServiceVoucher updateServiceVoucher(String id);
    
    void saveServiceVoucher(ServiceVoucher attr);
}