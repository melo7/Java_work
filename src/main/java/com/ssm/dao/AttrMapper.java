package com.ssm.dao;

import org.springframework.stereotype.Repository;

import com.ssm.model.Attr;
import com.ssm.model.User;
@Repository("attrMapper")
public interface AttrMapper {
   
    int insert(Attr attr);
    
    Attr getAttrByName(String name);
}