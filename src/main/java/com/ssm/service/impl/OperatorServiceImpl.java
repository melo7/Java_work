package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AgentMapper;
import com.ssm.dao.AttrMapper;
import com.ssm.dao.OperatorMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.Agent;
import com.ssm.model.Attr;
import com.ssm.model.Operator;
import com.ssm.model.ServicePackage;
import com.ssm.service.AgentService;
import com.ssm.service.AttrService;
import com.ssm.service.OperatorService;
import com.ssm.service.ServicePackageService;

@Service("operatorService")
@Transactional
public class OperatorServiceImpl implements OperatorService{
	
	@Resource(name="operatorMapper")
	private OperatorMapper operatorMapper;

	@Override
	public void insert(Operator attr) {
		operatorMapper.insert(attr);
		
	}

	@Override
	public void deleteOperator(String id) {
		operatorMapper.deleteOperator(id);
		
	}

	@Override
	public List<Operator> queryAllOperator() {
		
		return operatorMapper.queryAllOperator();
	}

	
}
