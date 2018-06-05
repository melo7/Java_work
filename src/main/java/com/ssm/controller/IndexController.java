package com.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.dao.AttrMapper;
import com.ssm.model.Attr;
import com.ssm.model.News;
import com.ssm.model.Sort;
import com.ssm.service.JsonViewFactory;
import com.ssm.service.NewsService;
import com.ssm.service.UserService;

import freemarker.template.utility.StringUtil;

@Controller
@RequestMapping("/llc")
public class IndexController extends BaseController {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private NewsService newsService;
	@Resource
	private AttrMapper attrMapper;
	
	/**
	 * 后台管理界面登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String, Object> login(Model model,
			@RequestParam (value = "username") String username,
			@RequestParam (value = "password") String password) throws MyException {
		try {
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			returnMap.put("message", "登录有误");
			if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
				Attr attr = attrMapper.getAttrByName(username);
				if (attr != null && StringUtils.equals(username, attr.getName())) {
					returnMap.put("code", "0");
					returnMap.put("message", "登录成功");
				}
			}
			return returnMap;
		} catch (Exception e) {
			throw new MyException(e);
		}
	}
	
	/**
	 * 添加文章
	 */
	@RequestMapping(value = "/insertArticleAdd")
	@ResponseBody
	public Map<String, Object> insertArticleAdd(Model model,
			@RequestParam(value = "article_title") String article_title,
			@RequestParam(value = "article_head") String article_head,
			@RequestParam(value = "article_sort") String article_sort,
			@RequestParam(value = "selectoption") String selectoption,
			@RequestParam(value = "article_info") String article_info){
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "0");
			returnMap.put("message", "insert success");
			return returnMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	/**
	 * 处理成功之后的跳转
	 * @param model
	 * @return
	 */
	@RequestMapping (value ="/successToLogin")
	public String successToLogin(){
		
//		return "detaiInfo";
		return "starter";
	}
	
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
//		Attr attr = new Attr();
//		attr.setName("ssssss");
//		attr.setNumber(214749364);
//		attr.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		attr.setPassword("123456");
//		attrMapper.insert(attr);
		return "login";
	}
	/**
	 * 测试插入数据
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("insertNews")
	public String insertNews() throws Exception{
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			Date date = new Date();
			News news = new News();
			news.setTitle("news");
			news.setAuthor("melon");
			news.setContent("dskhdkfhdhfjdfs");
			news.setCount(1);
			news.setTime(bartDateFormat.format(date));
			newsService.insertNews(news);
			return "index";
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	/**
	 * 测试查询数据
	 * @return
	 */
	@RequestMapping("selectTile")
	public String getTitleInfo(){
		
		News news = newsService.getTitleInfo("ggg");
		return "index";
	}
	
	/**
	 * 测试更新
	 */
	@RequestMapping(value="updateByTitle")
	public String updateByTitle(){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		Date date = new Date();
		News news = new News();
		news.setTitle("dsd");
		news.setAuthor("melon2");
		news.setContent("更改数据");
		news.setCount(2);
		news.setTime(bartDateFormat.format(date));
		newsService.updateByTitle(news);
		return "index";
	}
	
	/**
	 * 测试删除
	 * @throws Exception 
	 */
	@RequestMapping(value = "delectByTitle")
	@ResponseBody
	public String delectByTitle() throws MyException {
		try {
			newsService.delectByTitle("ggg");
			return "delect database success";
		} catch (Exception e) {
			throw new MyException("");
		}
	}
	
	/**
	 * 测试自定义异常
	 */
	@RequestMapping(value="throwsExc")
	public void throwsExc(){
		Object user = null;
        if(user == null){
            throw new MyException(CommonErrorCode.LuoErrorCode.NULL_OBJ);
        }
	}
}
