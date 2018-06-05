package com.ssm.controller;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;  
import java.util.Map; 

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Member;
import com.ssm.model.Personal;
import com.ssm.service.PersonalService;

@Controller
@RequestMapping("/personal")
public class PersonalController extends BaseController{
	
	@Resource
	private PersonalService personalService;
	
	
	/**
	 * 添加会员
	 * @param model
	 * @param name
	 * @param realname
	 * @param idNumber
	 * @param phone
	 * @param emall
	 * @param memberLevel
	 * @param adderss
	 * @return
	 */
	@RequestMapping(value = "/insertPersonal")
	@ResponseBody
	private Map<String, Object> insertPersonal(Model model,
			@RequestParam (value = "name") String name,
			@RequestParam (value = "realname") String realname,
			@RequestParam (value = "idNumber") String idNumber,
			@RequestParam (value = "phone") String phone,
			@RequestParam (value = "emall") String emall,
			@RequestParam (value = "memberLevel") String memberLevel,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "status") String status) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			returnMap.put("message", "此身份证号码已经是会员身份，不可重复添加");
			//对身份证、手机号码、邮箱做验证      1：身份证号码必须为18位2：邮箱地址不合法3：手机号码不正确
			int checkFlag = personalService.checkIdentifyInfomation(idNumber,phone,emall);
			if (checkFlag == 1) {
				returnMap.put("code", "2");
				returnMap.put("message", "身份证号码必须为18位");
			}else if (checkFlag == 2) {
				returnMap.put("code", "3");
				returnMap.put("message", "不是有效的手机号码");
			}else if (checkFlag == 3) {
				
				returnMap.put("code", "4");
				returnMap.put("message", "邮箱地址不合法"); 
			}
			//查询身份证号码是否已经是会员身份，是不允许重复添加
			Personal exMember = personalService.queryExtendPersonal(idNumber);
			if (StringUtils.isEmpty(exMember) && exMember == null) {
				Personal personal = new Personal();
				personal.setUserName(name);
				personal.setAdderss(adderss);
				personal.setRealName(realname);
				personal.setClientLevel(memberLevel);
				personal.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				personal.setEmall(emall);
				personal.setPhoneNumber(phone);
				personal.setIdNumber(idNumber);
				personal.setStatus(status);
				personalService.insertPersonal(personal);
				returnMap.put("code", "0");
				returnMap.put("message", "添加会员成功！");
				return returnMap;
			}
			return returnMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新会员界面已存在的会员信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showPersonalInfo")
	@ResponseBody
	public Map<String, Object> showPersonalInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
//			Member member = memberService.queryExtendm("666666");
			List<Personal> rmember = personalService.selectAllPersonal(); 
			reMap.put("length",rmember.size());
			reMap.put("memberList", rmember);
//			model.addAttribute("code", "22");
//			model.addAttribute("mList", member);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	@RequestMapping(value="deletePersonalInfo")
	@ResponseBody
	public Map<String, Object> toshowPersonalInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(id)) {
				personalService.deletePersonalInfo(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
//		Member member = new Member();
//		member.setUserName("1");
//		member.setAdderss("1");
//		member.setClientLevel("1");
//		member.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
//		member.setEmall("1");
//		member.setPhoneNumber("1");
//		member.setIdNumber("1");
//		memberService.insertMember(member);
	}
	
	/**
	 * 编辑更改信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updatePersonalInfo")
	@ResponseBody
	public Map<String, Object> updatePersonalInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Personal personal = personalService.updatePersonal(id);
				if (personal != null) {
					reMap.put("memberList", personal);
					reMap.put("code", "0");
				}
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 报错修改用户信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value = "savePersonalInfo")
	@ResponseBody
	public Map<String, Object> savePersonalInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "name") String name,
			@RequestParam (value = "realname") String realname,
			@RequestParam (value = "idNumber") String idNumber,
			@RequestParam (value = "phone") String phone,
			@RequestParam (value = "emall") String emall,
			@RequestParam (value = "memberLevel") String memberLevel,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "status") String status){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Personal personal = new Personal();
			personal.setId(id);
			personal.setUserName(name);
			personal.setAdderss(adderss);
			personal.setRealName(realname);
			personal.setClientLevel(memberLevel);
			personal.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
			personal.setEmall(emall);
			personal.setPhoneNumber(phone);
			personal.setIdNumber(idNumber);
			personal.setStatus(status);
			personalService.savePersonal(personal);
			reMap.put("code", "0");
			reMap.put("message", "添加会员成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
