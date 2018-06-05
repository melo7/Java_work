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
import com.ssm.common.AttrName;
import com.ssm.model.Agent;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.service.AgentService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;

@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController{
	
	@Resource
	private AgentService agentService;
	
	
	/**
	 * 添加代理商
	 * @param model
	 * @param agentGroupName
	 * @param agentPName
	 * @param createBy
	 * @param status
	 * @param adderss
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "agentGroupName") String agentGroupName,
			@RequestParam (value = "agentPName") String agentPName,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "adderss") String adderss) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(agentGroupName) && StringUtils.isNotBlank(agentPName) && StringUtils.isNotBlank(createBy)
					&& StringUtils.isNotBlank(status) && StringUtils.isNotBlank(adderss)) {
				Agent agent = new Agent();
				agent.setAgentGroupName(agentGroupName);
				agent.setAgentPName(agentPName);
				agent.setCreateBy(createBy);
				agent.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				agent.setUpdateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				agent.setStatus(status);
				agent.setAdderss(adderss);
				agentService.insert(agent);
				returnMap.put("code", "0");
				returnMap.put("message", "添加服务包成功！");
			}
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新代理商界面已存在的代理商列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showAgentInfo")
	@ResponseBody
	public Map<String, Object> showAgentInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Agent> agentList = agentService.queryAllAgent();
			reMap.put("length",agentList.size());
			reMap.put("agentList", agentList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteAgentInfo")
	@ResponseBody
	public Map<String, Object> deleteAgentInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				agentService.deleteAgent(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
}
