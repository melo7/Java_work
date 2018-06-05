package com.ssm.service;

import java.util.List;

import com.ssm.model.Menu;
import com.ssm.model.TreeItem;

public interface MenuService {
	
	 Menu getMenu(String id) ;
	
	 void saveMenu(Menu menu);
	 
	 void updateMenuSort(Menu menu);

	 List<TreeItem> queryAllTreeMenu();
	 
	 

}
