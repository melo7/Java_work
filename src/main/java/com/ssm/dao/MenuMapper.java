package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssm.model.Menu;
import com.ssm.model.TreeItem;

@Repository("menuMapper")
public interface MenuMapper {

	public List<TreeItem> findAllTopList();

	public List<TreeItem> findSecondListMenu(@Param("top")TreeItem top);

	
	
	
}
