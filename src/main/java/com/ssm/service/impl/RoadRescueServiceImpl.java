package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.RoadRescueMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.dao.ServiceVoucherMapper;
import com.ssm.model.Attr;
import com.ssm.model.RoadRescue;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.service.AttrService;
import com.ssm.service.RoadRescueService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;

@Service("roadRescueService")
@Transactional
public class RoadRescueServiceImpl implements RoadRescueService{
	
	@Resource(name="roadRescueMapper")
	private RoadRescueMapper roadRescueMapper;

	@Override
	public void insert(RoadRescue attr) {
		roadRescueMapper.insert(attr);
		
	}

	@Override
	public void deleteRoadRescue(String id) {
		roadRescueMapper.deleteRoadRescue(id);
		
	}

	@Override
	public List<RoadRescue> queryAllRoadRescue() {
		
		return roadRescueMapper.queryAllRoadRescue();
	}

	
}
