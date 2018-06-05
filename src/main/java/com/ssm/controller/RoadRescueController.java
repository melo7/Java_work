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
import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.RoadRescue;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.service.MerchantService;
import com.ssm.service.RoadRescueService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;

import freemarker.core._RegexBuiltins.split_reBI;

@Controller
@RequestMapping("/roadRescue")
public class RoadRescueController extends BaseController{
	
	@Resource
	private RoadRescueService roadRescueService;
	
	
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
			@RequestParam (value = "callName") String callName,
			@RequestParam (value = "rescueTime") String rescueTime,
			@RequestParam (value = "rescueAdderss") String rescueAdderss,
			@RequestParam (value = "rescueName") String rescueName,
			@RequestParam (value = "rescueResult") String rescueResult,
			@RequestParam (value = "remark") String remark) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(callName) && StringUtils.isNotBlank(rescueTime) && StringUtils.isNotBlank(rescueAdderss)) {
				RoadRescue sPackage = new RoadRescue();
				sPackage.setCallName(callName);
				sPackage.setRemark(remark);
				sPackage.setRescueAdderss(rescueAdderss);
				sPackage.setRescueResult(rescueResult);
				sPackage.setRescueTime(rescueTime);
				sPackage.setRescueName(rescueName);
				roadRescueService.insert(sPackage);
				returnMap.put("code", "0");
				returnMap.put("message", "添加成功！");
			}
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新服务包界面已存在的服务包列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showRoadRescueInfo")
	@ResponseBody
	public Map<String, Object> showRoadRescueInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<RoadRescue> serList = roadRescueService.queryAllRoadRescue(); 
			reMap.put("length",serList.size());
			reMap.put("roadRescueList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteRoadRescueInfo")
	@ResponseBody
	public Map<String, Object> deleteRoadRescueInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				roadRescueService.deleteRoadRescue(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
}
