package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.CashVoucherMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.dao.ServiceVoucherMapper;
import com.ssm.dao.SinginMapper;
import com.ssm.model.Attr;
import com.ssm.model.CashVoucher;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.model.Singin;
import com.ssm.service.AttrService;
import com.ssm.service.CashVoucherService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;
import com.ssm.service.SinginService;

@Service("singinService")
@Transactional
public class SinginServiceImpl implements SinginService{
	
	@Resource(name="singinMapper")
	private SinginMapper singinMapper;

	@Override
	public List<Singin> queryAllSingin() {
		
		return singinMapper.queryAllSingin();
	}

	
}
