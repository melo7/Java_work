package com.ssm.service;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Agent;
import com.ssm.model.Operator;
import com.ssm.model.ServicePackage;


public interface OperatorService {
	
	void insert(Operator attr);
    
    void deleteOperator(String id);
    
    List<Operator> queryAllOperator();
	
	
}
