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
import com.ssm.model.Agent;
import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;
import com.ssm.model.Merchant;
import com.ssm.model.ServicePackage;
import com.ssm.service.AgentService;
import com.ssm.service.CarService;
import com.ssm.service.MerchantService;
import com.ssm.service.ServicePackageService;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController{
	
	@Resource
	private CarService carService;
	
	
	/**
	 * 添加车辆服务
	 * @param model
	 * @param car_type
	 * @param service_type
	 * @param adderss
	 * @param price
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	private Map<String, Object> insert(Model model,
			@RequestParam (value = "car_type") String car_type,
			@RequestParam (value = "service_type") String service_type,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "price") String price,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "createBy") String createBy
			
			) {
		
		try {		
			Map<String,Object> returnMap = new HashMap<String, Object>();
			returnMap.put("code", "1");
			CarServiceModel cModel = new CarServiceModel();
				cModel.setCar_type(car_type);
				cModel.setService_type(service_type);
				cModel.setAdderss(adderss);
				cModel.setPrice(price);
				cModel.setStatus(status);
				cModel.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				cModel.setStartTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				cModel.setEndTime(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
				cModel.setC3("hz");
				cModel.setC4("sx");
				cModel.setCreateBy(createBy);
				carService.insert(cModel);
				returnMap.put("code", "0");
				returnMap.put("message", "添加车辆服务成功！");
			
				return returnMap;
			
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	/**
	 * 刷新车辆服务界面已存在的车辆服务列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showCarServiceInfo")
	@ResponseBody
	public Map<String, Object> showCarServiceInfo(Model model){
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<CarServiceModel> carServiceList = carService.queryAllCarServiceModel();
			reMap.put("length",carServiceList.size());
			reMap.put("carServiceList", carServiceList);
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	
	@RequestMapping(value="deleteCarServiceInfo")
	@ResponseBody
	public Map<String, Object> deleteCarServiceInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				carService.deleteCarServiceModel(id);
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
	@RequestMapping(value = "updateCarServiceInfo")
	@ResponseBody
	public Map<String, Object> updateCarServiceInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				CarServiceModel carServiceList = carService.updateCarServiceModel(id);
				if (carServiceList != null) {
					reMap.put("carServiceList", carServiceList);
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
	@RequestMapping (value = "saveCarServiceInfo")
	@ResponseBody
	public Map<String, Object> saveMemberInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "car_type") String car_type,
			@RequestParam (value = "service_type") String service_type,
			@RequestParam (value = "adderss") String adderss,
			@RequestParam (value = "price") String price,
			@RequestParam (value = "status") String status,
			@RequestParam (value = "createBy") String createBy){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			CarServiceModel cModel = new CarServiceModel();
			cModel.setId(id);
			cModel.setCar_type(car_type);
			cModel.setService_type(service_type);
			cModel.setAdderss(adderss);
			cModel.setPrice(price);
			cModel.setStatus(status);
			cModel.setCreateDate(new SimpleDateFormat("yyyy-MM-hh HH:mm:ss").format(new Date()));
			cModel.setCreateBy(createBy);
			carService.saveMemberCarServiceModel(cModel);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
}
