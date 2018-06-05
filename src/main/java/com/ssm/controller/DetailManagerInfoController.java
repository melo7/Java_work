package com.ssm.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.model.User;
import com.ssm.service.UserService;


@Controller
@RequestMapping("dellInfo")
public class DetailManagerInfoController extends BaseController{

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping(value="/insertManagerInfo")
	public void insertManagerInfo(
			@RequestParam (value = "userName") String userName,
			@RequestParam (value = "password") String password){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setAge(18);
		userService.insert(user);
	}
}
