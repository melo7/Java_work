package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.LogMapper;
import com.ssm.dao.PushMessageMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.dao.UserStatisticsMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Log;
import com.ssm.model.PushMessage;
import com.ssm.model.Store;
import com.ssm.model.UserStatistics;
import com.ssm.service.CarService;
import com.ssm.service.LogService;
import com.ssm.service.PushMessageService;
import com.ssm.service.StoreService;
import com.ssm.service.UserStatisticsService;

@Service("userStatisticsService")
@Transactional
public class UserStatisticsServiceImpl implements UserStatisticsService{

	@Resource(name="userStatisticsMapper")
	private UserStatisticsMapper userStatisticsMapper;

	@Override
	public void insert(UserStatistics attr) {
		userStatisticsMapper.insert(attr);
		
	}

	@Override
	public void deleteUserStatistics(String id) {
		userStatisticsMapper.deleteUserStatistics(id);
		
	}

	@Override
	public List<UserStatistics> queryAllUserStatistics() {
		
		return userStatisticsMapper.queryAllUserStatistics();
	}

	
}
