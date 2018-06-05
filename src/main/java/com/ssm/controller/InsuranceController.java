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

import com.ssm.model.Insurance;
import com.ssm.model.UserStatistics;
import com.ssm.service.InsuranceService;
import com.ssm.service.UserStatisticsService;

@Controller
@RequestMapping("/insurance")
public class InsuranceController extends BaseController{
	
	@Resource
	private InsuranceService insuranceService;
	
	
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
			@RequestParam (value = "insurance_id") String insurance_id,
			@RequestParam (value = "insurance_nbr") String insurance_nbr,
			@RequestParam (value = "insurance_introdu") String insurance_introdu,
			@RequestParam (value = "insurance_amount") String insurance_amount,
			@RequestParam (value = "insurance_status") String insurance_status
			) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			Insurance insurance = new Insurance();
			insurance.setInsurance_amount(insurance_amount);
			insurance.setInsurance_endTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
			insurance.setInsurance_introdu(insurance_introdu);
			insurance.setInsurance_nbr(insurance_nbr);
			insurance.setInsurance_status(insurance_status);
				
			insuranceService.insert(insurance);
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
	@RequestMapping(value="showInsuranceInfo")
	@ResponseBody
	public Map<String, Object> showInsuranceInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Insurance> insuranceList = insuranceService.queryAllInsurance();
			reMap.put("length",insuranceList.size());
			reMap.put("insuranceList", insuranceList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteInsuranceInfo")
	@ResponseBody
	public Map<String, Object> deleteInsuranceInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				insuranceService.deleteInsurance(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
}
