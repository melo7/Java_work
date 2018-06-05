package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ssm.dao.PersonalMapper;
import com.ssm.model.Personal;
import com.ssm.service.PersonalService;;
@Service("personalService")
@Transactional
public class PersonalServiceImpl implements PersonalService{

	@Resource
	private PersonalMapper personalMapper;
	
	
	/**
	 * 校验身份证、手机号码、邮箱合法性
	 * 1：身份证号码必须为18位
	 * 2：邮箱地址不合法
	 * 3：手机号码不正确
	 */
	@Override
	public int checkIdentifyInfomation(String idNumber, String phone,
			String emall) {
		int flag = 0;
		if (idNumber != null && idNumber.length()<18) {
			flag =1;
		}
		if (!isMobileNo(phone)) {
			flag = 2;
		}
		if (!org.apache.commons.lang.StringUtils.contains(emall, "@")) {
			flag = 3;
		}
		return flag;
	}

	/**
	 * 正则表达式验证手机号码正确性
	 */
	public boolean isMobileNo(String mobileNo){
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobileNo);
		
		return matcher.matches();
	}
//	public boolean isMobileNO(String mobileNo){



	
	

	@Override
	public void insertPersonal(Personal personal) {
		personalMapper.insertPersonal(personal);
		
	}

	@Override
	public Personal queryExtendPersonal(String idNumber) {
		
		return personalMapper.queryExtendPersonal(idNumber);
	}

	@Override
	public List<Personal> selectAllPersonal() {
		List<Personal> list = null;
		list = personalMapper.selectAllPersonal();
		if (list != null) {
//			for (Member member : list) {
//				
//			}
		}
		return  list;
	}

	/**
	 * 删除会员信息显示
	 */
	@Override
	public void deletePersonalInfo(String id) {
		personalMapper.deletePersonalInfo(id);
		
	}

	@Override
	public Personal updatePersonal(String id) {
		
		return personalMapper.updatePersonal(id);
	}

	@Override
	public void savePersonal(Personal member) {
		personalMapper.savePersonal(member);
		
	}
		
		//A
//	    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//	    Matcher m = p.matcher(mobileNo);
//	    return m.matches();
		//B
//		String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";    
//		　　Pattern p = Pattern.compile(regExp);    
//		　　Matcher m = p.matcher(value);    
//		　　return m.find();
//	　　}
	
}
