package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.NewsMapper;
import com.ssm.dao.UserMapper;
import com.ssm.model.News;
import com.ssm.service.NewsService;
@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService{
	
	@Resource(name = "newsMapper")
	private NewsMapper newsMapper;

	@Override
	public void insertNews(News news) {
		newsMapper.insert(news);
	}

	@Override
	public News getTitleInfo(String title) {
		// TODO Auto-generated method stub
		return newsMapper.getTitleInfo(title);
	}

	@Override
	public void delectByTitle(String title) {
		newsMapper.delectByTitle(title);
		
	}

	@Override
	public void updateByTitle(News news) {
		newsMapper.updateByTitle(news);
		
	}
}
