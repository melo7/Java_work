package com.ssm.dao;

import org.springframework.stereotype.Repository;

import com.ssm.model.News;

@Repository("newsMapper")
public interface NewsMapper {
	
	//插入新闻
	void insert(News news);
	
	News getTitleInfo(String title);
	
	void updateByTitle(News news);
	
	void delectByTitle(String title);

}
