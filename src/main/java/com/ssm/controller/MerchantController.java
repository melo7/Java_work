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

import org.springframework.beans.factory.annotation.Value;
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
import com.ssm.model.Merchant;
import com.ssm.service.MerchantService;

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController{
	
	@Resource
	private MerchantService merchantService;
	
	
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
	@RequestMapping(value = "/insertMerchant")
	@ResponseBody
	private Map<String, Object> insertMerchant(Model model,
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
			int checkFlag = merchantService.checkIdentifyInfomation(idNumber,phone,emall);
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
			Merchant exMerchant = merchantService.queryExtendm(idNumber);
			if (StringUtils.isEmpty(exMerchant) && exMerchant == null) {
				Merchant merchant = new Merchant();
				merchant.setUserName(name);
				merchant.setAdderss(adderss);
				merchant.setRealName(realname);
				merchant.setClientLevel(memberLevel);
				merchant.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				merchant.setEmall(emall);
				merchant.setPhoneNumber(phone);
				merchant.setIdNumber(idNumber);
				merchant.setStatus(status);
				merchantService.insertMerchant(merchant);
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
	@RequestMapping(value="showMerchantInfo")
	@ResponseBody
	public Map<String, Object> showMerchantInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
//			Member member = memberService.queryExtendm("666666");
			List<Merchant> rmember = merchantService.selectAllMerchant(); 
			reMap.put("length",rmember.size());
			reMap.put("memberList", rmember);
//			model.addAttribute("code", "22");
//			model.addAttribute("mList", member);
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
	@RequestMapping(value = "updateMerchantInfo")
	@ResponseBody
	public Map<String, Object> updateMerchantInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Merchant merchant = merchantService.updateMerchant(id);
				if (merchant != null) {
					reMap.put("merchantList", merchant);
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
	@RequestMapping (value = "saveMerchantInfo")
	@ResponseBody
	public Map<String, Object> saveMerchantInfo(Model model,
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
			Merchant merchant = new Merchant();
			merchant.setId(id);
			merchant.setUserName(name);
			merchant.setAdderss(adderss);
			merchant.setRealName(realname);
			merchant.setClientLevel(memberLevel);
			merchant.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
			merchant.setEmall(emall);
			merchant.setPhoneNumber(phone);
			merchant.setIdNumber(idNumber);
			merchant.setStatus(status);
			merchantService.saveMerchant(merchant);
			reMap.put("code", "0");
			reMap.put("message", "添加会员成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
	
	@RequestMapping(value="deleteMerchantInfo")
	@ResponseBody
	public Map<String, Object> toshowMerchantInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(id)) {
				merchantService.deleteMerchantInfo(id);
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
}
