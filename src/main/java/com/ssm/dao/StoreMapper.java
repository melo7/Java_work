package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;
import com.ssm.model.Store;

@Repository("storeMapper")
public interface StoreMapper {
   
    void insert(Store attr);
    
    void deleteStore(String id);
    
    List<Store> queryAllStore();
    
    Store updateStore(String id);
    
    void saveStoreStore(Store attr);
}