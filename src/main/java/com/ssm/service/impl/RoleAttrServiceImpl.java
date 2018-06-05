package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.RoleAttrMapper;
import com.ssm.model.RoleAttr;
import com.ssm.service.RoleAttrService;

@Service("roleAttrService")
@Transactional
public class RoleAttrServiceImpl implements RoleAttrService{

	@Resource
	private RoleAttrMapper roleAttrMapper;
	
	/**
	 * 插入登录用户信息
	 */
	@Override
	public void insertRoleAttr(RoleAttr roleAttr) {
		roleAttrMapper.insertRoleAttr(roleAttr);
		
	}

	/**
	 * 连表查询用户职权
	 */
	@Override
	public RoleAttr getRoleAttrByName(String name) {
		RoleAttr roleAttr = new RoleAttr();
		roleAttrMapper.getRoleAttrByName(name);
		return roleAttr;
	}

}
