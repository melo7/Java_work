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

import com.ssm.model.Cooperator;
import com.ssm.service.CooperatorService;


@Controller
@RequestMapping("/cooperator")
public class CooperatorController extends BaseController{
	
	@Resource
	private CooperatorService cooperatorService;
	
	
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
			@RequestParam (value = "cooCompanyName") String cooCompanyName,
			@RequestParam (value = "cooName") String cooName,
			@RequestParam (value = "city") String city,
			@RequestParam (value = "cooPNumber") String cooPNumber,
			@RequestParam (value = "createBy") String createBy) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(cooCompanyName) && StringUtils.isNotBlank(cooName) && StringUtils.isNotBlank(createBy)) {
				Cooperator cooperator = new Cooperator();
				cooperator.setCooCompanyName(cooCompanyName);
				cooperator.setCooName(cooName);
				cooperator.setCity(city);
				cooperator.setCooPNumber(cooPNumber);
				cooperator.setCreateBy(createBy);
				cooperator.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				cooperatorService.insert(cooperator);
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
	@RequestMapping(value="showCooperatorInfo")
	@ResponseBody
	public Map<String, Object> showCooperatorInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Cooperator> serList = cooperatorService.queryAllCooperator(); 
			reMap.put("length",serList.size());
			reMap.put("cooperatorList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteCooperatorInfo")
	@ResponseBody
	public Map<String, Object> deleteCooperatorInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				cooperatorService.deleteCooperator(id);
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
	@RequestMapping(value = "updateCooperatorInfo")
	@ResponseBody
	public Map<String, Object> updateCooperatorInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Cooperator cooperatorList = cooperatorService.updateCooperator(id);
				if (cooperatorList != null) {
					reMap.put("cooperatorList", cooperatorList);
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
	@RequestMapping (value = "saveCooperatorInfo")
	@ResponseBody
	public Map<String, Object> saveCooperatorInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "cooCompanyName") String cooCompanyName,
			@RequestParam (value = "cooName") String cooName,
			@RequestParam (value = "city") String city,
			@RequestParam (value = "cooPNumber") String cooPNumber,
			@RequestParam (value = "createBy") String createBy){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Cooperator cModel = new Cooperator();
			cModel.setId(id);
			cModel.setCooName(cooName);
			cModel.setCooCompanyName(cooCompanyName);
			cModel.setCooPNumber(cooPNumber);
			cModel.setCity(city);
			cModel.setCreateBy(createBy);
			cooperatorService.saveServicePackage(cModel);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
