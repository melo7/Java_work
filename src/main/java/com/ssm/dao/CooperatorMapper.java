package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Cooperator;

@Repository("cooperatorMapper")
public interface CooperatorMapper {
   
    void insert(Cooperator attr);
    
    void deleteCooperator(String id);
    
    List<Cooperator> queryAllCooperator();
    
    Cooperator updateCooperator(String id);
    
    void saveCooperator(Cooperator attr);
}