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
import com.ssm.service.CashVoucherService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;

@Controller
@RequestMapping("/cashVoucher")
public class CashVoucherController extends BaseController{
	
	@Resource
	private CashVoucherService cashVoucherService;
	
	
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
			@RequestParam (value = "packageName") String packageName,
			@RequestParam (value = "packageNbr") String packageNbr,
			@RequestParam (value = "createBy") String createBy) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(packageName) && StringUtils.isNotBlank(packageNbr) && StringUtils.isNotBlank(createBy)) {
				CashVoucher sPackage = new CashVoucher();
				sPackage.setPackageName(packageName);
				sPackage.setPackageNbr(packageNbr);
				sPackage.setCreateBy(createBy);
				sPackage.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				sPackage.setStartTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				sPackage.setEndTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				cashVoucherService.insert(sPackage);
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
	@RequestMapping(value="showCashVoucherInfo")
	@ResponseBody
	public Map<String, Object> showCashVoucherInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<CashVoucher> serList = cashVoucherService.queryAllCashVoucher(); 
			reMap.put("length",serList.size());
			reMap.put("cashVoucherList", serList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteCashVoucherInfo")
	@ResponseBody
	public Map<String, Object> deleteCashVoucherInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				cashVoucherService.deleteCashVoucher(id);
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
	@RequestMapping(value = "updateCashVoucherInfo")
	@ResponseBody
	public Map<String, Object> updateCashVoucherInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				CashVoucher cashVoucherList = cashVoucherService.updateCashVoucher(id);
				if (cashVoucherList != null) {
					reMap.put("cashVoucherList", cashVoucherList);
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
	@RequestMapping (value = "saveCashVoucherInfo")
	@ResponseBody
	public Map<String, Object> saveCashVoucherInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "packageName") String packageName,
			@RequestParam (value = "packageNbr") String packageNbr,
			@RequestParam (value = "createBy") String createBy){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			CashVoucher cModel = new CashVoucher();
			cModel.setId(id);
			cModel.setPackageName(packageName);
			cModel.setPackageNbr(packageNbr);
			cModel.setCreateBy(createBy);
			cashVoucherService.saveCashVoucher(cModel);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
