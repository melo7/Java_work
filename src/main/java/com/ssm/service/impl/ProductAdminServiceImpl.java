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
import com.ssm.dao.ProductAdminMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Attr;
import com.ssm.model.Cooperator;
import com.ssm.model.OrderAdmin;
import com.ssm.model.ProductAdmin;
import com.ssm.model.ServicePackage;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.AttrService;
import com.ssm.service.CooperatorService;
import com.ssm.service.OrderAdminService;
import com.ssm.service.ProductAdminService;
import com.ssm.service.ServicePackageService;

@Service("productAdminService")
@Transactional
public class ProductAdminServiceImpl implements ProductAdminService{
	
	@Resource(name="productAdminMapper")
	private ProductAdminMapper productAdminMapper ;

	@Override
	public void insert(ProductAdmin attr) {
		productAdminMapper.insert(attr);
		
	}

	@Override
	public void deleteProductAdmin(String id) {
		productAdminMapper.deleteProductAdmin(id);
		
	}

	@Override
	public List<ProductAdmin> queryAllProductAdmin() {
		
		return productAdminMapper.queryAllProductAdmin();
	}

	@Override
	public ProductAdmin updateProductAdmin(String id) {
		
		return productAdminMapper.updateProductAdmin(id);
	}

	@Override
	public void saveProductAdmin(ProductAdmin attr) {
		productAdminMapper.saveProductAdmin(attr);
		
	}

}
