package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.IntegralMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.Attr;
import com.ssm.model.Integral;
import com.ssm.model.ServicePackage;
import com.ssm.service.AttrService;
import com.ssm.service.IntegralService;
import com.ssm.service.ServicePackageService;

@Service("integralService")
@Transactional
public class IntegralServiceImpl implements IntegralService{
	
	@Resource(name="integralMapper")
	private IntegralMapper integralMapper;

	@Override
	public void deleteIntegral(String id) {
		integralMapper.deleteIntegral(id);
		
	}

	@Override
	public List<Integral> queryAllIntegral() {
		
		return integralMapper.queryAllIntegral();
	}

	@Override
	public Integral updateIntegral(String id) {
		
		return integralMapper.updateIntegral(id);
	}

	@Override
	public void saveIntegral(Integral attr) {
		integralMapper.saveIntegral(attr);
		
	}

	

}
