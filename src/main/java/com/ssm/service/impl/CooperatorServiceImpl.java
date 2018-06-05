package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.CooperatorMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.Attr;
import com.ssm.model.Cooperator;
import com.ssm.model.ServicePackage;
import com.ssm.service.AttrService;
import com.ssm.service.CooperatorService;
import com.ssm.service.ServicePackageService;

@Service("cooperatorService")
@Transactional
public class CooperatorServiceImpl implements CooperatorService{
	
	@Resource(name="cooperatorMapper")
	private CooperatorMapper cooperatorMapper;

	@Override
	public void insert(Cooperator attr) {
		cooperatorMapper.insert(attr);
		
	}

	@Override
	public void deleteCooperator(String id) {
		cooperatorMapper.deleteCooperator(id);
		
	}

	@Override
	public List<Cooperator> queryAllCooperator() {
		
		return cooperatorMapper.queryAllCooperator();
	}

	@Override
	public Cooperator updateCooperator(String id) {
		
		return cooperatorMapper.updateCooperator(id);
	}

	@Override
	public void saveServicePackage(Cooperator attr) {
		cooperatorMapper.saveCooperator(attr);
		
	}

	
}
