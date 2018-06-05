package com.ssm.service;

import org.springframework.stereotype.Repository;

import com.ssm.model.Attr;


public interface AttrService {
	
	Attr getAttrByName(String name);
	void insert(Attr attr);
	
	
}
