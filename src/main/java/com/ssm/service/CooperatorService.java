package com.ssm.service;


import java.util.List;

import com.ssm.model.Cooperator;


public interface CooperatorService {
	
	void insert(Cooperator attr);
    
    void deleteCooperator(String id);
    
    List<Cooperator> queryAllCooperator();
	
    public Cooperator updateCooperator(String id);
    
    void saveServicePackage(Cooperator attr);
}
