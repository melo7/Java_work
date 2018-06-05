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
import com.ssm.model.PushMessage;
import com.ssm.model.ServicePackage;
import com.ssm.model.Store;
import com.ssm.service.AgentService;
import com.ssm.service.CarService;
import com.ssm.service.LogService;
import com.ssm.service.MerchantService;
import com.ssm.service.PushMessageService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.StoreService;

@Controller
@RequestMapping("/pushMessage")
public class PushMessageController extends BaseController{
	
	@Resource
	private PushMessageService pushMessageService;
	
	
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
			@RequestParam (value = "sort") String sort,
			@RequestParam (value = "title") String title,
			@RequestParam (value = "introdu") String introdu,
			@RequestParam (value = "content") String content
			) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			PushMessage pMessage = new PushMessage();
			pMessage.setSort(sort);
			pMessage.setIntrodu(introdu);
			pMessage.setContent(content);
			pMessage.setTitle(title);
			pMessage.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				
			pushMessageService.insert(pMessage);
			returnMap.put("code", "0");
			returnMap.put("message", "添加推送信息成功！");
		
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
	@RequestMapping(value="showPushMessageInfo")
	@ResponseBody
	public Map<String, Object> showPushMessageInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<PushMessage> pushMessageList = pushMessageService.queryAllPushMessage();
			reMap.put("length",pushMessageList.size());
			reMap.put("pushMessageList", pushMessageList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deletePushMessageInfo")
	@ResponseBody
	public Map<String, Object> deletePushMessageInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				pushMessageService.deletePushMessage(id);
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
	@RequestMapping(value = "updatePushMessageInfo")
	@ResponseBody
	public Map<String, Object> updatePushMessageInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				PushMessage pushMessageList = pushMessageService.updatePushMessage(id);
				if (pushMessageList != null) {
					reMap.put("pushMessageList", pushMessageList);
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
	@RequestMapping (value = "savePushMessageInfo")
	@ResponseBody
	public Map<String, Object> savePushMessageInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "sort") String sort,
			@RequestParam (value = "title") String title,
			@RequestParam (value = "introdu") String introdu,
			@RequestParam (value = "content") String content){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			PushMessage pMessage = new PushMessage();
			pMessage.setId(id);
			pMessage.setSort(sort);
			pMessage.setTitle(title);
			pMessage.setContent(content);
			pMessage.setIntrodu(introdu);
			pushMessageService.savePushMessage(pMessage);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
