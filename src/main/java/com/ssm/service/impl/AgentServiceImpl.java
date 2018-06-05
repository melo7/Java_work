package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AgentMapper;
import com.ssm.dao.AttrMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.Agent;
import com.ssm.model.Attr;
import com.ssm.model.ServicePackage;
import com.ssm.service.AgentService;
import com.ssm.service.AttrService;
import com.ssm.service.ServicePackageService;

@Service("agentService")
@Transactional
public class AgentServiceImpl implements AgentService{
	
	@Resource(name="agentMapper")
	private AgentMapper agentMapper;

	@Override
	public void insert(Agent attr) {
		agentMapper.insert(attr);
		
	}

	@Override
	public void deleteAgent(String id) {
		agentMapper.deleteAgent(id);
		
	}

	@Override
	public List<Agent> queryAllAgent() {
		return agentMapper.queryAllAgent();
	}

	
}
