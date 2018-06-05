package com.ssm.service;

import com.ssm.model.RoleAttr;


public interface RoleAttrService {

	void insertRoleAttr(RoleAttr roleAttr);
	
	RoleAttr getRoleAttrByName(String name);
}
