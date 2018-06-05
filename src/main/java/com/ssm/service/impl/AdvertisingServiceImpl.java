package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AdderssAdminMapper;
import com.ssm.dao.AdvertisingMapper;
import com.ssm.dao.AttrMapper;
import com.ssm.dao.CooperatorMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Advertising;
import com.ssm.model.Attr;
import com.ssm.model.Cooperator;
import com.ssm.model.ServicePackage;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.AdvertisingService;
import com.ssm.service.AttrService;
import com.ssm.service.CooperatorService;
import com.ssm.service.ServicePackageService;

@Service("advertisingService")
@Transactional
public class AdvertisingServiceImpl implements AdvertisingService{
	
	@Resource(name="advertisingMapper")
	private AdvertisingMapper advertisingMapper;

	@Override
	public void insert(Advertising attr) {
		advertisingMapper.insert(attr);
		
	}

	@Override
	public void deleteAdvertising(String id) {
		advertisingMapper.deleteAdvertising(id);
		
	}

	@Override
	public List<Advertising> queryAllAdvertising() {
		
		return advertisingMapper.queryAllAdvertising();
	}

	@Override
	public Advertising updateAdvertising(String id) {
		
		return advertisingMapper.updateAdvertising(id);
	}

	@Override
	public void saveAdvertising(Advertising attr) {
		advertisingMapper.saveAdvertising(attr);
		
	}
}
