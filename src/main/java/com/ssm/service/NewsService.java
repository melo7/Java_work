package com.ssm.service;

import com.ssm.model.News;

public interface NewsService {

	void insertNews(News news);
	
	News getTitleInfo(String title);
	
	void updateByTitle(News news);
	
	void delectByTitle(String title);
}
