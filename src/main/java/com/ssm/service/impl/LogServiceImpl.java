package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.LogMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Log;
import com.ssm.model.Store;
import com.ssm.service.CarService;
import com.ssm.service.LogService;
import com.ssm.service.StoreService;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService{

	@Resource(name="logMapper")
	private LogMapper logMapper;

	@Override
	public void insert(Log attr) {
		logMapper.insert(attr);
		
	}

	@Override
	public void deleteLog(String id) {
		logMapper.deleteLog(id);
		
	}

	@Override
	public List<Log> queryAllLog() {
		
		return logMapper.queryAllLog();
	}

	@Override
	public Log updateLog(String id) {
		
		return logMapper.updateLog(id);
	}

	@Override
	public void saveLog(Log attr) {
		logMapper.saveLog(attr);
		
	}

	
}
