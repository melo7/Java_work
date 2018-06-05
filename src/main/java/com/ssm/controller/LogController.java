package com.ssm.controller;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;  

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Agent;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Log;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.model.Store;
import com.ssm.service.AgentService;
import com.ssm.service.CarService;
import com.ssm.service.LogService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.StoreService;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController{
	
	@Resource
	private LogService logService;
	
	
	/**
	 * 添加系统信息
	 * @param model
	 * @param sort
	 * @param title
	 * @param introdu
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "sort") String sort,
			@RequestParam (value = "title") String title,
			@RequestParam (value = "introdu") String introdu,
			@RequestParam (value = "content") String content,
			@RequestParam (value = "status") String status
			) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			Log log = new Log();
				log.setSort(sort);
				log.setStatus(status);
				log.setIntrodu(introdu);
				log.setContent(content);
				log.setTitle(title);
				log.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				
				logService.insert(log);
				returnMap.put("code", "0");
				returnMap.put("message", "添加店铺信息成功！");
			
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
	@RequestMapping(value="showLogInfo")
	@ResponseBody
	public Map<String, Object> showLogInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Log> logList = logService.queryAllLog();
			reMap.put("length",logList.size());
			reMap.put("logList", logList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteLogInfo")
	@ResponseBody
	public Map<String, Object> deleteLogInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				logService.deleteLog(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateLogInfo")
	@ResponseBody
	public Map<String, Object> updateLogInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Log logList = logService.updateLog(id);
				if (logList != null) {
					reMap.put("logList", logList);
					reMap.put("code", "0");
				}
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value = "saveLogInfo")
	@ResponseBody
	public Map<String, Object> saveLogInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "sort") String sort,
			@RequestParam (value = "title") String title,
			@RequestParam (value = "introdu") String introdu,
			@RequestParam (value = "content") String content,
			@RequestParam (value = "status") String status){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Log log = new Log();
			log.setId(id);
			log.setSort(sort);
			log.setStatus(status);
			log.setTitle(title);
			log.setContent(content);
			log.setIntrodu(introdu);
			logService.saveLog(log);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
