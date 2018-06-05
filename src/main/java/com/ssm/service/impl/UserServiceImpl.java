package com.ssm.service.impl;

import javax.annotation.Resource;





import com.ssm.dao.AttrMapper;
import com.ssm.dao.UserMapper;
import com.ssm.model.Attr;
import com.ssm.model.User;
import com.ssm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Resource(name="attrMapper")
	private AttrMapper attrMapper;
	
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User getUser(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User findUserByUserName(String username) {
		return userMapper.findUserByUserName(username);
	}

	@Override
	public void insertId(Attr attr) {
		attrMapper.insert(attr);
		
	}
}