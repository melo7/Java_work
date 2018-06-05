package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Agent;
import com.ssm.model.Attr;
import com.ssm.model.ServicePackage;
import com.ssm.model.User;
@Repository("agentMapper")
public interface AgentMapper {
   
    void insert(Agent attr);
    
    void deleteAgent(String id);
    
    List<Agent> queryAllAgent();
}