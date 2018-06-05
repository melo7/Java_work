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
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.model.Store;
import com.ssm.service.AgentService;
import com.ssm.service.CarService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController extends BaseController{
	
	@Resource
	private StoreService storeService;
	
	
	/**
	 * 添加店铺信息
	 * @param model
	 * @param storeName
	 * @param adderss
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "storeName") String storeName,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "status") String status
			) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			Store store = new Store();
				store.setStoreName(storeName);
				store.setStatus(status);
				store.setAdderss(adderss);
				store.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				
				storeService.insert(store);
				returnMap.put("code", "0");
				returnMap.put("message", "添加店铺信息成功！");
			
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新添加店铺界面已存在的店铺服务列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showStoreInfo")
	@ResponseBody
	public Map<String, Object> showStoreInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Store> storeList = storeService.queryAllStore();
			reMap.put("length",storeList.size());
			reMap.put("storeList", storeList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteStoreInfo")
	@ResponseBody
	public Map<String, Object> deleteStoreInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				storeService.deleteStore(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 编辑更改信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateStoreInfo")
	@ResponseBody
	public Map<String, Object> updateStoreInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Store storeList = storeService.updateStore(id);
				if (storeList != null) {
					reMap.put("storeList", storeList);
					reMap.put("code", "0");
				}
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 服务信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value = "saveStoreInfo")
	@ResponseBody
	public Map<String, Object> saveStoreInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "storeName") String storeName,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "status") String status){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Store store = new Store();
			store.setId(id);
			store.setStoreName(storeName);
			store.setStatus(status);
			store.setAdderss(adderss);
			storeService.saveMemberStore(store);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
