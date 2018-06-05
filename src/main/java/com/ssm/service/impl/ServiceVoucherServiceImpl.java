package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.dao.ServiceVoucherMapper;
import com.ssm.model.Attr;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.service.AttrService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;

@Service("serviceVoucherService")
@Transactional
public class ServiceVoucherServiceImpl implements ServiceVoucherService{
	
	@Resource(name="serviceVoucherMapper")
	private ServiceVoucherMapper serviceVoucherMapper;

	@Override
	public void insert(ServiceVoucher attr) {
		serviceVoucherMapper.insert(attr);
		
	}

	@Override
	public void deleteServiceVoucher(String id) {
		serviceVoucherMapper.deleteServiceVoucher(id);
		
	}

	@Override
	public List<ServiceVoucher> queryAllServiceVoucher() {
		
		return serviceVoucherMapper.queryAllServiceVoucher();
	}

	@Override
	public ServiceVoucher updateServiceVoucher(String id) {
		
		return serviceVoucherMapper.updateServiceVoucher(id);
	}

	@Override
	public void saveServiceVoucher(ServiceVoucher attr) {
		serviceVoucherMapper.saveServiceVoucher(attr);
		
	}
}
