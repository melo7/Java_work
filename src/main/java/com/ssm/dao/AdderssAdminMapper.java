package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.AdderssAdmin;


@Repository("adderssAdminMapper")
public interface AdderssAdminMapper {
   
    void insert(AdderssAdmin attr);
    
    void deleteAdderssAdmin(String id);
    
    List<AdderssAdmin> queryAllAdderssAdmin();
    
    AdderssAdmin updateAdderssAdmin(String id);
    
    void saveAdderssAdmin(AdderssAdmin attr);
}