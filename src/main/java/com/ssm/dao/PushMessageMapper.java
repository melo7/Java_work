package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.PushMessage;



@Repository("pushMessageMapper")
public interface PushMessageMapper {
   
    void insert(PushMessage attr);
    
    void deletePushMessage(String id);
    
    List<PushMessage> queryAllPushMessage();
    
    PushMessage updatePushMessage(String id);
    
    void savePushMessage(PushMessage attr);
}