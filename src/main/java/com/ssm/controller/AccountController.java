package com.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.model.Attr;
import com.ssm.model.RoleAttr;
import com.ssm.service.AttrService;
import com.ssm.service.RoleAttrService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private static final String ROLE_ATTR_OPTERTION = "cjgly/ptgly/cgy";
	
	@Resource
	private RoleAttrService roleAttrService;
	@Resource
	private AttrService attrService;
	
	@RequestMapping(value="/doAccount")
	@ResponseBody
	public Map<String, Object> doAccount(Model model,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password){
		Map<String , Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", "0");
		returnMap.put("msg", "success");
		returnMap.put("role", "1");
		
		try {
			
//			Attr attr = new Attr();
//			attr.setName(name);
//			attr.setPassword(password);
//			Attr returnAttr = attrService.getAttrByName(name);
//			if (StringUtils.isEmpty(returnAttr) && returnAttr == null) {
//				attrService.insert(attr);
//			}
//			RoleAttr roleAttr = new RoleAttr();
//			roleAttr.setName(name);
//			roleAttr.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			roleAttr.setPosition(ROLE_ATTR_OPTERTION);
//			RoleAttr rAttr = roleAttrService.getRoleAttrByName(name);
//			if (StringUtils.isEmpty(rAttr) && roleAttr == null) {
//				roleAttrService.insertRoleAttr(roleAttr);
//			}
//			returnMap.put("roleAttr", ROLE_ATTR_OPTERTION);
			return returnMap;
		} catch (Exception e) {
			throw new MyException(); 
		}
	}
}
