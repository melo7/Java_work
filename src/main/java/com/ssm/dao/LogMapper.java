package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Log;


@Repository("logMapper")
public interface LogMapper {
   
    void insert(Log attr);
    
    void deleteLog(String id);
    
    List<Log> queryAllLog();
    
    Log updateLog(String id);
    
    void saveLog(Log attr);
}