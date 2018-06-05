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
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.Operator;
import com.ssm.model.ServicePackage;
import com.ssm.service.AgentService;
import com.ssm.service.MerchantService;
import com.ssm.service.OperatorService;
import com.ssm.service.ServicePackageService;

@Controller
@RequestMapping("/operator")
public class OperatorController extends BaseController{
	
	@Resource
	private OperatorService operatorService;
	
	
	/**
	 * 添加合作商
	 * @param model
	 * @param operatorName
	 * @param operatorPName
	 * @param createBy
	 * @param status
	 * @param adderss
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "operatorName") String operatorName,
			@RequestParam (value = "operatorPName") String operatorPName,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "adderss") String adderss) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(operatorName) && StringUtils.isNotBlank(operatorPName) && StringUtils.isNotBlank(createBy)
					&& StringUtils.isNotBlank(status) && StringUtils.isNotBlank(adderss)) {
				Operator operator = new Operator();
				operator.setOperatorName(operatorName);
				operator.setOperatorPName(operatorPName);
				operator.setCreateBy(createBy);
				operator.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				operator.setUpdateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				operator.setStatus(status);
				operator.setAdderss(adderss);
				operatorService.insert(operator);
				returnMap.put("code", "0");
				returnMap.put("message", "添加服务包成功！");
			}
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新合作商界面已存在的合作商列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showOperatorInfo")
	@ResponseBody
	public Map<String, Object> showOperatorInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Operator> operatorList = operatorService.queryAllOperator();
			reMap.put("length",operatorList.size());
			reMap.put("operatorList", operatorList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteOperatorInfo")
	@ResponseBody
	public Map<String, Object> deleteOperatorInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				operatorService.deleteOperator(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
}
