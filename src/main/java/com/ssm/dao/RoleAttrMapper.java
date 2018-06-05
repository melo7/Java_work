package com.ssm.dao;

import org.springframework.stereotype.Repository;

import com.ssm.model.RoleAttr;

@Repository("roleAttrMapper")
public interface RoleAttrMapper {
	
	void insertRoleAttr(RoleAttr roleAttr);
	
	RoleAttr getRoleAttrByName(String name);
}
