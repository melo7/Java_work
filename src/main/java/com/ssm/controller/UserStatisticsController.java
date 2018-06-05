package com.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;  

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.model.UserStatistics;
import com.ssm.service.UserStatisticsService;

@Controller
@RequestMapping("/userStatistics")
public class UserStatisticsController extends BaseController{
	
	@Resource
	private UserStatisticsService userStatisticsService;
	
	
	/**
	 * 
	 * @param model
	 * @param sort
	 * @param title
	 * @param introdu
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "singup") String singup,
			@RequestParam (value = "upNbr") String upNbr) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			UserStatistics pMessage = new UserStatistics();
			pMessage.setSingup(singup);
			pMessage.setUpNbr(upNbr);
			pMessage.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				
			userStatisticsService.insert(pMessage);
			returnMap.put("code", "0");
			returnMap.put("message", "添加成功！");
		
			return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showUserStatisticsInfo")
	@ResponseBody
	public Map<String, Object> showUserStatisticsInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<UserStatistics> userStatisticsList = userStatisticsService.queryAllUserStatistics();
			reMap.put("length",userStatisticsList.size());
			reMap.put("userStatisticsList", userStatisticsList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteUserStatisticsInfo")
	@ResponseBody
	public Map<String, Object> deleteUserStatisticsInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				userStatisticsService.deleteUserStatistics(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
}
