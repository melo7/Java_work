package com.ssm.dao;

import org.springframework.stereotype.Repository;

import com.ssm.model.User;
@Repository("userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByUserName(String username);
}