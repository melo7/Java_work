package com.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.model.Menu;
import com.ssm.model.TreeItem;
import com.ssm.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuService menuService;

	
	/**
	 * 获取数据库菜单栏
	 */
	@RequestMapping("/show")
	public void showMenuTree(HttpServletRequest request,HttpServletResponse response,Model model){
		List<TreeItem> list = menuService.queryAllTreeMenu();
		sendJSONArray(list, response);
	}
	
	
	
	
	
	
	
	
	public MenuService getMenuService() {
		return menuService;
	}
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
}
