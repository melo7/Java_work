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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.JsonViewFactory;
import com.ssm.common.Result;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Cooperator;
import com.ssm.model.OrderAdmin;
import com.ssm.model.ProductAdmin;
import com.ssm.model.error.ApplicationAccessException;
import com.ssm.model.error.HtmlErrorPageException;
import com.ssm.service.AdderssAdminService;
import com.ssm.service.CooperatorService;
import com.ssm.service.OrderAdminService;
import com.ssm.service.ProductAdminService;


@Controller
@RequestMapping("/productAdmin")
public class ProductAdminController extends BaseController{
	
	@Resource
	private ProductAdminService productAdminService;
	
	
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
			@RequestParam (value = "productName") String productName,
			@RequestParam (value = "productNbr") String productNbr,
			@RequestParam (value = "productType") String productType,
			@RequestParam (value = "phoneNumber") String phoneNumber,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "productPrice") String productPrice,
			@RequestParam (value = "status") String status) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			
			if (StringUtils.isNotBlank(productName) && StringUtils.isNotBlank(productNbr) && StringUtils.isNotBlank(createBy)) {
				ProductAdmin admin = new ProductAdmin();
				admin.setCreateBy(createBy);
				admin.setPhoneNumber(phoneNumber);
				admin.setProductName(productName);
				admin.setProductNbr(productNbr);
				admin.setProductPrice(productPrice);
				admin.setProductType(productType);
				admin.setStatus(status);
				admin.setCreated(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				productAdminService.insert(admin);
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
	@RequestMapping(value="showProductAdminInfo")
	@ResponseBody
	public Result showProductAdminInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<ProductAdmin> serList = productAdminService.queryAllProductAdmin();
			reMap.put("length",serList.size());
			reMap.put("productAdminList", serList);
			return Result.ok(reMap);
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteProductAdminInfo")
	@ResponseBody
	public Map<String, Object> deleteProductAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				productAdminService.deleteProductAdmin(id);
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
	@RequestMapping(value = "updateProductAdminInfo")
	@ResponseBody
	public Map<String, Object> updateProductAdminInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				ProductAdmin productAdminList = productAdminService.updateProductAdmin(id);
				if (productAdminList != null) {
					reMap.put("productAdminList", productAdminList);
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
	 * @throws HtmlErrorPageException 
	 * @throws ApplicationAccessException 
	 */
	@RequestMapping (value = "saveProductAdminInfo")
	@ResponseBody
	public Map<String, Object> saveProductAdminInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "productName") String productName,
			@RequestParam (value = "productNbr") String productNbr,
			@RequestParam (value = "productType") String productType,
			@RequestParam (value = "phoneNumber") String phoneNumber,
			@RequestParam (value = "createBy") String createBy,
			@RequestParam (value = "productPrice") String productPrice,
			@RequestParam (value = "status") String status) throws HtmlErrorPageException{
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			ProductAdmin admin = new ProductAdmin();
			admin.setId(id);
			admin.setCreateBy(createBy);
			admin.setPhoneNumber(phoneNumber);
			admin.setProductName(productName);
			admin.setProductNbr(productNbr);
			admin.setProductPrice(productPrice);
			admin.setProductType(productType);
			admin.setStatus(status);
			productAdminService.saveProductAdmin(admin);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
//			return JsonViewFactory.buildJsonView(reMap);
		} catch (Exception e) {
			throw new HtmlErrorPageException("error");
//			throw new ApplicationAccessException(e);
		}
		
	}
}
