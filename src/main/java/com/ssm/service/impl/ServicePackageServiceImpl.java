package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.Attr;
import com.ssm.model.ServicePackage;
import com.ssm.service.AttrService;
import com.ssm.service.ServicePackageService;

@Service("servicePackageService")
@Transactional
public class ServicePackageServiceImpl implements ServicePackageService{
	
	@Resource(name="servicePackageMapper")
	private ServicePackageMapper servicePackageMapper;

	@Override
	public void insert(ServicePackage attr) {
		servicePackageMapper.insert(attr);
		
	}

	@Override
	public void deleteServicePackage(String id) {
		servicePackageMapper.deleteServicePackage(id);
		
	}

	@Override
	public List<ServicePackage> queryAllServicePackage() {
		
		return servicePackageMapper.queryAllServicePackage();
	}

	@Override
	public ServicePackage updateServicePackage(String id) {
		
		return servicePackageMapper.updateServicePackage(id);
	}

	@Override
	public void saveServicePackage(ServicePackage attr) {
		servicePackageMapper.saveServicePackage(attr);
		
	}

	

}
