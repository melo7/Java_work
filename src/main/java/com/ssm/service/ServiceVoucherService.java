package com.ssm.service;


import java.util.List;

import com.ssm.model.ServiceVoucher;



public interface ServiceVoucherService {
	
	void insert(ServiceVoucher attr);
    
    void deleteServiceVoucher(String id);
    
    List<ServiceVoucher> queryAllServiceVoucher();
	
    public ServiceVoucher updateServiceVoucher(String id);
    
    void saveServiceVoucher(ServiceVoucher attr);
}
