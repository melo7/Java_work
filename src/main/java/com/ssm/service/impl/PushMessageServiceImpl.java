package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.LogMapper;
import com.ssm.dao.PushMessageMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Log;
import com.ssm.model.PushMessage;
import com.ssm.model.Store;
import com.ssm.service.CarService;
import com.ssm.service.LogService;
import com.ssm.service.PushMessageService;
import com.ssm.service.StoreService;

@Service("pushMessageService")
@Transactional
public class PushMessageServiceImpl implements PushMessageService{

	@Resource(name="pushMessageMapper")
	private PushMessageMapper pushMessageMapper;

	@Override
	public void insert(PushMessage attr) {
		pushMessageMapper.insert(attr);
		
	}

	@Override
	public void deletePushMessage(String id) {
		pushMessageMapper.deletePushMessage(id);
		
	}

	@Override
	public List<PushMessage> queryAllPushMessage() {
		
		return pushMessageMapper.queryAllPushMessage();
	}

	@Override
	public PushMessage updatePushMessage(String id) {
		
		return pushMessageMapper.updatePushMessage(id);
	}

	@Override
	public void savePushMessage(PushMessage attr) {
		pushMessageMapper.savePushMessage(attr);
		
	}

	
}
