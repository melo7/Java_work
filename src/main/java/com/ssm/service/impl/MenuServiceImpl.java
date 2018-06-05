package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.MenuMapper;
import com.ssm.model.Menu;
import com.ssm.model.TreeItem;
import com.ssm.service.MenuService;


@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
		
	@Autowired
	private MenuMapper menuMapper;
	//-- Menu Service --//

	@Override
	public List<TreeItem> queryAllTreeMenu() {
		 List<TreeItem> list = menuMapper.findAllTopList();
		for (TreeItem top : list) {
			TreeItem treeItem = new TreeItem();
			top.setChildren(menuMapper.findSecondListMenu(top));
		}
		return list;
	}
	
	@Override
	public Menu getMenu(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateMenuSort(Menu menu) {
		// TODO Auto-generated method stub
		
	}
	public MenuMapper getMenuMapper() {
		return menuMapper;
	}
	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}
	

}