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
import com.ssm.model.CarServiceModel;
import com.ssm.model.CashVoucher;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.model.Singin;
import com.ssm.service.CashVoucherService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;
import com.ssm.service.SinginService;

@Controller
@RequestMapping("/singin")
public class SinginController extends BaseController{
	
	@Resource
	private SinginService singinService;
	
	
	/**
	 * 刷新列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showSinginInfo")
	@ResponseBody
	public Map<String, Object> showSinginInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Singin> serList = singinService.queryAllSingin(); 
			reMap.put("length",serList.size());
			reMap.put("singinList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	
}
