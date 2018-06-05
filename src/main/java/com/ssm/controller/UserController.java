package com.ssm.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ssm.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.model.User;
import com.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger LOG = Logger.getLogger(UserController.class);
//	@Resource
	private UserService userService;

	// 最好是将@Resource放在setter方法上，因为这样更符合面向对象的思想，通过set、get去操作属性，而不是直接去操作属性。
	@Resource(name = "userService")
	public void UserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	public String query(Model model) {
		User user = userService.getUser("1");
		model.addAttribute("user", user);
		LOG.error(user.toString());
		return "index";
	}
}