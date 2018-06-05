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

import com.ssm.model.AdderssAdmin;
import com.ssm.model.Cooperator;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.CooperatorService;


@Controller
@RequestMapping("/adderssAdmin")
public class AdderssAdminController extends BaseController{
	
	@Resource
	private AdderssAdminService adderssAdminService;
	
	
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
			@RequestParam (value = "expressName") String expressName,
			@RequestParam (value = "expressPhone") String expressPhone,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "expressNature") String expressNature,
			@RequestParam (value = "adderssDetail") String adderssDetail,
			@RequestParam (value = "createBy") String createBy) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(expressName) && StringUtils.isNotBlank(expressPhone) && StringUtils.isNotBlank(createBy)) {
				AdderssAdmin admin = new AdderssAdmin();
				admin.setAdderss(adderssDetail);
				admin.setAdderssDetail(adderssDetail);
				admin.setCreateBy(createBy);
				admin.setExpressName(expressName);
				admin.setExpressNature(expressNature);
				admin.setExpressPhone(expressPhone);
				admin.setCreateTime(new SimpleDateFormat("yyyy-MM-hh HH-mm-ss").format(new Date()));
				adderssAdminService.insert(admin);
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
	@RequestMapping(value="showAdderssAdminInfo")
	@ResponseBody
	public Map<String, Object> showAdderssAdminInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<AdderssAdmin> serList = adderssAdminService.queryAllAdderssAdmin(); 
			reMap.put("length",serList.size());
			reMap.put("adderssAdminList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteAdderssAdminInfo")
	@ResponseBody
	public Map<String, Object> deleteAdderssAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				adderssAdminService.deleteAdderssAdmin(id);
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
	@RequestMapping(value = "updateAdderssAdminInfo")
	@ResponseBody
	public Map<String, Object> updateAdderssAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				AdderssAdmin adderssAdminList = adderssAdminService.updateAdderssAdmin(id);
				if (adderssAdminList != null) {
					reMap.put("adderssAdminList", adderssAdminList);
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
	@RequestMapping (value = "saveAdderssAdminInfo")
	@ResponseBody
	public Map<String, Object> saveAdderssAdminInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "expressName") String expressName,
			@RequestParam (value = "expressPhone") String expressPhone,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "expressNature") String expressNature,
			@RequestParam (value = "adderssDetail") String adderssDetail,
			@RequestParam (value = "createBy") String createBy){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			AdderssAdmin admin = new AdderssAdmin();
			admin.setId(id);
			admin.setAdderss(adderssDetail);
			admin.setAdderssDetail(adderssDetail);
			admin.setCreateBy(createBy);
			admin.setExpressName(expressName);
			admin.setExpressNature(expressNature);
			admin.setExpressPhone(expressPhone);
			adderssAdminService.saveAdderssAdmin(admin);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
