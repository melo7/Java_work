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
import com.ssm.dao.MemberMapper;
import com.ssm.model.Member;
import com.ssm.service.MemberService;
@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService{

	@Resource
	private MemberMapper memberMapper;
	
	@Override
	public void insertMember(Member member) {
		memberMapper.insertMember(member);
		
	}

	@Override
	public Member queryExtendm(String idNumber) {
		
		return memberMapper.queryExtendm(idNumber);
	}
	
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
	public List<Member> selectAllMember() {
		List<Member> list = null;
		list = memberMapper.selectAllMember();
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
	public void deleteMemberInfo(String id) {
		memberMapper.deleteMemberInfo(id);
		
	}

	@Override
	public Member updateMember(String id) {
		
		return memberMapper.updateMember(id);
	}

	@Override
	public void saveMember(Member member) {
		memberMapper.saveMember(member);
		
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
