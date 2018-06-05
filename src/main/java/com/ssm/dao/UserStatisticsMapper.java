package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.UserStatistics;




@Repository("userStatisticsMapper")
public interface UserStatisticsMapper {
   
    void insert(UserStatistics attr);
    
    void deleteUserStatistics(String id);
    
    List<UserStatistics> queryAllUserStatistics();
    
    UserStatistics updateUserStatistics(String id);
    
    void saveUserStatistics(UserStatistics attr);
}