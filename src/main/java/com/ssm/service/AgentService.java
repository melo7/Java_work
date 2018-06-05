package com.ssm.service;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Agent;
import com.ssm.model.ServicePackage;


public interface AgentService {
	
void insert(Agent attr);
    
    void deleteAgent(String id);
    
    List<Agent> queryAllAgent();
	
	
}
