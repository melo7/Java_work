package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AdderssAdminMapper;
import com.ssm.dao.AttrMapper;
import com.ssm.dao.CooperatorMapper;
import com.ssm.dao.OrderAdminMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Attr;
import com.ssm.model.Cooperator;
import com.ssm.model.OrderAdmin;
import com.ssm.model.ServicePackage;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.AttrService;
import com.ssm.service.CooperatorService;
import com.ssm.service.OrderAdminService;
import com.ssm.service.ServicePackageService;

@Service("orderAdminService")
@Transactional
public class OrderAdminServiceImpl implements OrderAdminService{
	
	@Resource(name="orderAdminMapper")
	private OrderAdminMapper orderAdminMapper ;

	@Override
	public void insert(OrderAdmin attr) {
		orderAdminMapper.insert(attr);
		
	}

	@Override
	public void deleteOrderAdmin(String id) {
		orderAdminMapper.deleteOrderAdmin(id);
		
	}

	@Override
	public List<OrderAdmin> queryAllOrderAdmin() {
		
		return orderAdminMapper.queryAllOrderAdmin();
	}

	@Override
	public OrderAdmin updateOrderAdmin(String id) {
		
		return orderAdminMapper.updateOrderAdmin(id);
	}

	@Override
	public void saveOrderAdmin(OrderAdmin attr) {
		orderAdminMapper.saveOrderAdmin(attr);
		
	}
	

	

	
}
