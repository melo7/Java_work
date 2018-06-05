package com.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.model.News;
import com.ssm.service.NewsService;


@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private NewsService newsService;
	
	@RequestMapping(value = "insertNews")
	public void insertNews(){
//		SimpleDateFormat bartDateFormat = new SimpleDateFormat
//			("yyyy-mm-dd"); 
//		Date date = new Date();
//		News news = new News();
//		news.setTitle("news");
//		news.setAuthor("melon");
//		news.setContent("dskhdkfhdhfjdfs");
//		news.setCount(1);
//		news.setTime(bartDateFormat.format(date));
//		newsService.insertNews(news);
	}
	
}
