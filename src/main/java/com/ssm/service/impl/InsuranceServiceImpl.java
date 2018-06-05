package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.InsuranceMapper;
import com.ssm.dao.LogMapper;
import com.ssm.dao.PushMessageMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.dao.UserStatisticsMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Insurance;
import com.ssm.model.Log;
import com.ssm.model.PushMessage;
import com.ssm.model.Store;
import com.ssm.model.UserStatistics;
import com.ssm.service.CarService;
import com.ssm.service.InsuranceService;
import com.ssm.service.LogService;
import com.ssm.service.PushMessageService;
import com.ssm.service.StoreService;
import com.ssm.service.UserStatisticsService;

@Service("insuranceService")
@Transactional
public class InsuranceServiceImpl implements InsuranceService{

	@Resource(name="insuranceMapper")
	private InsuranceMapper insuranceMapper;

	@Override
	public void insert(Insurance attr) {
		insuranceMapper.insert(attr);
		
	}

	@Override
	public void deleteInsurance(String id) {
		insuranceMapper.deleteInsurance(id);
		
	}

	@Override
	public List<Insurance> queryAllInsurance() {
		
		return insuranceMapper.queryAllInsurance();
	}

	
}
