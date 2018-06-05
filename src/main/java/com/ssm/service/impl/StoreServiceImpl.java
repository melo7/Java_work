package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.dao.StoreMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Store;
import com.ssm.service.CarService;
import com.ssm.service.StoreService;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService{

	@Resource(name="storeMapper")
	private StoreMapper storeMapper;

	@Override
	public void insert(Store attr) {
		storeMapper.insert(attr);
		
	}

	@Override
	public void deleteStore(String id) {
		storeMapper.deleteStore(id);
		
	}

	@Override
	public List<Store> queryAllStore() {
		
		return storeMapper.queryAllStore();
	}

	@Override
	public Store updateStore(String id) {
		
		return storeMapper.updateStore(id);
	}

	@Override
	public void saveMemberStore(Store attr) {
		storeMapper.saveStoreStore(attr);
		
	}

	

	
}
