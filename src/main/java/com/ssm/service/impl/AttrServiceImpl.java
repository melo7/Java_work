package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.model.Attr;
import com.ssm.service.AttrService;

@Service("attrService")
@Transactional
public class AttrServiceImpl implements AttrService{
	
	@Resource(name="attrMapper")
	private AttrMapper attrMapper;

	@Override
	public Attr getAttrByName(String name) {
		
		return attrMapper.getAttrByName(name);
	}

	@Override
	public void insert(Attr attr) {
		attrMapper.insert(attr);
		
	}

}
