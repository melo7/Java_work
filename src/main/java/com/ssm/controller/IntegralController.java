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
import com.ssm.model.Integral;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.service.IntegralService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;

@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController{
	
	@Resource
	private IntegralService integralService;
	
	
	/**
	 * 刷新列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showIntegralInfo")
	@ResponseBody
	public Map<String, Object> showIntegralInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Integral> serList = integralService.queryAllIntegral(); 
			reMap.put("length",serList.size());
			reMap.put("integralList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteIntegralInfo")
	@ResponseBody
	public Map<String, Object> deleteIntegralInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				integralService.deleteIntegral(id);
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
	@RequestMapping(value = "updateIntegralInfo")
	@ResponseBody
	public Map<String, Object> updateIntegralInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Integral integralList = integralService.updateIntegral(id);
				if (integralList != null) {
					reMap.put("integralList", integralList);
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
	@RequestMapping (value = "saveIntegralInfo")
	@ResponseBody
	public Map<String, Object> saveIntegralInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "integralName") String integralName,
			@RequestParam (value = "integralAmount") String integralAmount){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Integral cModel = new Integral();
			cModel.setId(id);
			cModel.setIntegralName(integralName);
			cModel.setIntegralAmount(integralAmount);
			integralService.saveIntegral(cModel);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
