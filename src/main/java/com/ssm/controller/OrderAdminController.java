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

import com.alibaba.fastjson.JSONObject;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Cooperator;
import com.ssm.model.OrderAdmin;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.CooperatorService;
import com.ssm.service.OrderAdminService;


@Controller
@RequestMapping("/orderAdmin")
public class OrderAdminController extends BaseController{
	
	@Resource
	private OrderAdminService orderAdminService;
	
	
	/**
	 * 添加
	 * @param model
	 * @param packageName
	 * @param packageNbr
	 * @param createBy
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "orderNum") String orderNum,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "orderType") String orderType,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "phoneNumber") String phoneNumber) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(orderNum) && StringUtils.isNotBlank(orderType) && StringUtils.isNotBlank(createBy)) {
				OrderAdmin admin = new OrderAdmin();
				admin.setOrderNum(orderNum);
				admin.setOrderType(orderType);
				admin.setCreateBy(createBy);
				admin.setStatus(status);
				admin.setPhoneNumber(phoneNumber);
				admin.setCreated(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				orderAdminService.insert(admin);
				returnMap.put("code", "0");
				returnMap.put("message", "添加成功！");
			}
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showOrderAdminInfo")
	@ResponseBody
	public Map<String, Object> showOrderAdminInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<OrderAdmin> serList = orderAdminService.queryAllOrderAdmin();
			System.out.println(JSONObject.toJSONString(serList));
			reMap.put("length",serList.size());
			reMap.put("orderAdminList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteOrderAdminInfo")
	@ResponseBody
	public Map<String, Object> deleteOrderAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				orderAdminService.deleteOrderAdmin(id);
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
	@RequestMapping(value = "updateOrderAdminInfo")
	@ResponseBody
	public Map<String, Object> updateOrderAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				OrderAdmin orderAdminList = orderAdminService.updateOrderAdmin(id);
				if (orderAdminList != null) {
					reMap.put("orderAdminList", orderAdminList);
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
	@RequestMapping (value = "saveOrderAdminInfo")
	@ResponseBody
	public Map<String, Object> saveOrderAdminInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "orderNum") String orderNum,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "orderType") String orderType,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "phoneNumber") String phoneNumber){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			OrderAdmin admin = new OrderAdmin();
			admin.setId(id);
			admin.setOrderNum(orderNum);
			admin.setOrderType(orderType);
			admin.setCreateBy(createBy);
			admin.setStatus(status);
			admin.setPhoneNumber(phoneNumber);
			orderAdminService.saveOrderAdmin(admin);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
