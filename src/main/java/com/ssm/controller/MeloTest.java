package com.ssm.controller;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.ssm.common.AttrName;
import com.ssm.dao.AttrMapper;
import com.ssm.model.Attr;
import com.ssm.model.Member;
import com.ssm.service.MemberService;

@Controller
public class MeloTest extends BaseController{

//	@Resource(name = "attrMapper")
//	private AttrMapper attrMapper;
	@Resource
	private MemberService memberService;
	
	public static void main(String[] args) {
//		SimpleDateFormat bartDateFormat = new SimpleDateFormat
//  				("yyyy-mm-dd HH:mm:ss"); 
//		Date date = new Date(); 
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		Attr attr = new Attr();
//		attr.setName("ssssss");
//		attr.setNumber(1000000);
//		attr.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		attr.setPassword("123456");
//		attrMapper.
		
//		Member member = new Member();
//		member.setUserName("jre");
//		member.setAddress("china");
//		member.setClientLevel("VIP");
//		member.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
//		member.setMall("melon@face.com");
//		member.setPhoneNumber("88888888");
//		member.setIdNumber("666666666666666");
//		memberService.insertMember(member);
//		String iString = "";
//		System.out.println(iString.length());
//		String phone = "18888888888";
//		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//		Matcher matcher = pattern.matcher(phone);
//		System.out.println(matcher.matches());
//		String emall = "phone=8&emall=8&name=melon&realname=2&idNumber=8&memberLevel=%E5%B8%AE%E5%8A%A9%E4%B8%AD%E5%BF%83&adderss=8";
//		Date time= new java.sql.Date(newjava.util.Date().getTime());
//		System.out.println(new Date(0));
		if (StringUtils.equals("admin", AttrName.Login.ADMIN)) {
			System.out.println("ture");
		}
		
		
	}
	
//	public boolean isMobileNo(String mobileNo){
//		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//		Matcher matcher = pattern.matcher(mobileNo);
//		
//		return matcher.matches();
//	}
}
