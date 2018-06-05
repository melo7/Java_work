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

import com.ssm.model.ConsumptionStatistics;
import com.ssm.model.UserStatistics;
import com.ssm.service.ConsumptionStatisticsService;
import com.ssm.service.UserStatisticsService;

@Controller
@RequestMapping("/consumptionStatistics")
public class ConsumptionStatisticsController extends BaseController{
	
	@Resource
	private ConsumptionStatisticsService consumptionStatisticsService;
	
	
	/**
	 * 
	 * @param model
	 * @param integral
	 * @param service
	 * @param platform
	 * @param line
	 * @param serviceq
	 * @param djq
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "integral") String integral,
			@RequestParam (value = "service") String service,
			@RequestParam (value = "platform") String platform,
			@RequestParam (value = "line") String line,
			@RequestParam (value = "serviceq") String serviceq,
			@RequestParam (value = "djq") String djq) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			ConsumptionStatistics pMessage = new ConsumptionStatistics();
			pMessage.setIntegral(integral);
			pMessage.setLine(line);
			pMessage.setPlatform(platform);
			pMessage.setService(serviceq);
			pMessage.setServiceq(serviceq);
			pMessage.setDjq(djq);
			pMessage.setCreateDate(new SimpleDateFormat("yyyy-MM-hh").format(new Date()));
				
			consumptionStatisticsService.insert(pMessage);
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
	@RequestMapping(value="showConsumptionStatisticsInfo")
	@ResponseBody
	public Map<String, Object> showConsumptionStatisticsInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<ConsumptionStatistics> consumptionStatisticsList = consumptionStatisticsService.queryAllConsumptionStatistics();
			reMap.put("length",consumptionStatisticsList.size());
			reMap.put("consumptionStatisticsList", consumptionStatisticsList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteConsumptionStatisticsInfo")
	@ResponseBody
	public Map<String, Object> deleteConsumptionStatisticsInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				consumptionStatisticsService.deleteConsumptionStatistics(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
}
