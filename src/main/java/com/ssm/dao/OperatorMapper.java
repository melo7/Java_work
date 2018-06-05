package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Agent;
import com.ssm.model.Attr;
import com.ssm.model.Operator;
import com.ssm.model.ServicePackage;
import com.ssm.model.User;
@Repository("operatorMapper")
public interface OperatorMapper {
   
    void insert(Operator attr);
    
    void deleteOperator(String id);
    
    List<Operator> queryAllOperator();
}