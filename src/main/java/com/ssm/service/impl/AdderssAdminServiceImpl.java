package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AdderssAdminMapper;
import com.ssm.dao.AttrMapper;
import com.ssm.dao.CooperatorMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Attr;
import com.ssm.model.Cooperator;
import com.ssm.model.ServicePackage;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.AttrService;
import com.ssm.service.CooperatorService;
import com.ssm.service.ServicePackageService;

@Service("adderssAdminService")
@Transactional
public class AdderssAdminServiceImpl implements AdderssAdminService{
	
	@Resource(name="adderssAdminMapper")
	private AdderssAdminMapper adderssAdminMapper;

	@Override
	public void insert(AdderssAdmin attr) {
		adderssAdminMapper.insert(attr);
	}

	@Override
	public void deleteAdderssAdmin(String id) {
		adderssAdminMapper.deleteAdderssAdmin(id);
	}

	@Override
	public List<AdderssAdmin> queryAllAdderssAdmin() {
		
		return adderssAdminMapper.queryAllAdderssAdmin();
	}

	@Override
	public AdderssAdmin updateAdderssAdmin(String id) {
		
		return adderssAdminMapper.updateAdderssAdmin(id);
	}

	@Override
	public void saveAdderssAdmin(AdderssAdmin attr) {
		adderssAdminMapper.saveAdderssAdmin(attr);
		
	}

	
}
