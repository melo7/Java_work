package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.ConsumptionStatisticsMapper;
import com.ssm.dao.LogMapper;
import com.ssm.dao.PushMessageMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.dao.UserStatisticsMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.ConsumptionStatistics;
import com.ssm.model.Log;
import com.ssm.model.PushMessage;
import com.ssm.model.Store;
import com.ssm.model.UserStatistics;
import com.ssm.service.CarService;
import com.ssm.service.ConsumptionStatisticsService;
import com.ssm.service.LogService;
import com.ssm.service.PushMessageService;
import com.ssm.service.StoreService;
import com.ssm.service.UserStatisticsService;

@Service("consumptionStatisticsService")
@Transactional
public class ConsumptionStatisticsServiceImpl implements ConsumptionStatisticsService{

	@Resource(name="consumptionStatisticsMapper")
	private ConsumptionStatisticsMapper consumptionStatisticsMapper;

	@Override
	public void insert(ConsumptionStatistics attr) {
		consumptionStatisticsMapper.insert(attr);
		
	}

	@Override
	public void deleteConsumptionStatistics(String id) {
		consumptionStatisticsMapper.deleteConsumptionStatistics(id);
		
	}

	@Override
	public List<ConsumptionStatistics> queryAllConsumptionStatistics() {
		
		return consumptionStatisticsMapper.queryAllConsumptionStatistics();
	}

	
	
}
