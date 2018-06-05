package com.ssm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 连接OBD接口
 * @author win7
 *
 */
@Controller
@RequestMapping("/OBD")
public class OBDController {
	
	/**
	 * obd注册接口
	 */
	@RequestMapping("/OBDRegister")
	@ResponseBody
	public String obdRegister(String develop_id,String app_id){
		//注册请求URL
		String URL = "http://open.api.dbscar.com/?action=develop.reg_user";
		//创建map 保存参数
		Map<String,String> map = new LinkedHashMap<String, String>();
		//按照文档参数顺序添加参数
		map.put("app_id","2013120200000002");
		map.put("develop_id", "1000");
		//map.put("time",Long.toString(System.currentTimeMillis()));
		map.put("time", "1404296554a8f93c4e9f2ab7dcbef012cd8b5147");
		//将map进行遍历添加& 参数 = 参数值; 
		StringBuffer  obdRegisterStr = new StringBuffer();
		for (String str : map.keySet()) {
	        if (map.get(str) == null || map.get(str).isEmpty() || map.get(str).equals("")){
	            continue;
	        }					  
	        obdRegisterStr.append("&"+ str + "=" + map.get(str));
	    }
		//MD5加密字符串 获取签名sign
		System.out.println(URL.substring(28) + obdRegisterStr.toString()+"-----------------");
		String sign =MD5.code(URL.substring(28) + obdRegisterStr.toString());
		//获取time内容
		int last = obdRegisterStr.lastIndexOf("=");
		String oldTime = obdRegisterStr.substring(last + 1);
		//1.通过time获取10位时间戳
		//String newTime =String.valueOf(Long.parseLong(obdRegisterStr.substring(last + 1))/1000);
		//2.截取前10位
		String newTime = oldTime.substring(0, 10);
		//时间戳替代原时间
		obdRegisterStr.replace(last, obdRegisterStr.length(), "="+newTime);
		//拼接URL 进行请求第三方接口
		String registerUrl = URL + obdRegisterStr.toString() + "&" +"sign=" + sign;
        System.out.println(registerUrl);
        /*
         	使用httpConnection进行url请求
	        return getResponse(registerUrl);
        */
        //使用httpClient进行请求url请求
        return HttpClientUtil.doGet(registerUrl);
    }
	
	/**
	 *obd 根据设备序列号获取设备信息
	 */
	@RequestMapping("/OBDPage")
	@ResponseBody
	public String obdPage(String develop_id,String devicesn,String app_id,String devicetype){
		
		String URL = "http://open.api.dbscar.com/?action=data_develop.get_devices_info";
		
		//获取参数
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("app_id", "242EE0B0-43BB-A4D0-DC4F-1999D166D4D3");
		map.put("develop_id", "1000");
		map.put("devicesn", "971190000018");
		map.put("devicetype", "golo3CU");
		//map.put("time", Long.toString(System.currentTimeMillis()));
		map.put("time", "1404296554a8f93c4e9f2ab7dcbef012cd8b5147");
		
		StringBuffer obdPage = new StringBuffer();
		
		for (String str : map.keySet()) {
	        if (map.get(str) == null || map.get(str).isEmpty() || map.get(str).equals("")){
	            continue;
	        }					  
	        obdPage.append("&"+ str + "=" + map.get(str));
	    }
		//MD5加密字符串 获取签名sign
		String sign =MD5.code(URL.substring(28) + obdPage.toString());
		System.out.println(sign);
		//获取time内容
		int last = obdPage.lastIndexOf("=");
		String oldTime = obdPage.substring(last + 1);
		//1.通过time获取10位时间戳
		//String newTime =String.valueOf(Long.parseLong(obdPage.substring(last + 1))/1000);
		//2.截取前10位
		String newTime = oldTime.substring(0, 10);
		//时间戳替代原时间
		obdPage.replace(last, obdPage.length(), "="+newTime);
		//拼接URL 进行请求第三方接口
		String pageUrl = URL + obdPage.toString() + "&" +"sign=" + sign;
		System.out.println(pageUrl);
		
		/*
	     	使用httpConnection进行url请求
	        return getResponse(pageUrl);
		*/
		
		
		//使用httpClient进行请求url请求
		return HttpClientUtil.doGet(pageUrl);
	}
	
	
	/**
	 *obd 激活接头
	 */
	@RequestMapping("/OBDSplice")
	@ResponseBody
	public String obdSplice(String serial_no,String develop_id,String mine_car_plate_num,String code_id,
			String car_carcase_num,String app_id,String car_type_id,String car_producing_year,String display_lan,
			String car_displacement,String car_gearbox_type,String password,String access_id,String access_token
			){
		
		String URL = "http://open.api.dbscar.com/?action=forjth.save_car_config";
		
		//获取参数
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("develop_id", "10094");
		map.put("mine_car_plate_num", "A6P771");
		map.put("code_id", "1");
		map.put("serial_no", "\\'967790128164\\'");
		map.put("car_carcase_num", "LBV3A23C4D3089208");
		map.put("app_id", "2013120200000001");
		map.put("display_lan", "cn");
		map.put("car_type_id", "BARCHETTA");
		map.put("car_producing_year", "2012");
		map.put("car_displacement", "2.0L");
		map.put("car_gearbox_type", "1");
		map.put("password", "14720507");
		map.put("access_id", "44233");
		map.put("access_token", "golo_68155_1435567073");
		map.put("time", "1426232715a8f93c4e9f2ab7dcbef012cd8b5147");
		
		
		StringBuffer obdSplice = new StringBuffer();
		
		for (String str : map.keySet()) {
	        if (map.get(str) == null || map.get(str).isEmpty() || map.get(str).equals("")){
	            continue;
	        }					  
	        obdSplice.append("&"+ str + "=" + map.get(str));
	    }
		
		//MD5加密字符串 获取签名sign
		String sign =MD5.code(URL.substring(28) + obdSplice.toString());
		System.out.println(sign+"----");
		
		
		
		
		
		
		
		
		
		
		
		/*
	     	使用httpConnection进行url请求
	        return getResponse(pageUrl);
		*/
		
		
		//使用httpClient进行请求url请求
		//return HttpClientUtil.doGet(pageUrl);
		return "11";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 使用httpConnection进行url请求
	 * @param requsetUrl
	 * @return
	 */
	private static String getResponse(String requsetUrl) {
        try {
            URL url = new URL(requsetUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true); // 使用 URL 连接进行输出
            httpConn.setDoInput(true); // 使用 URL 连接进行输入
            httpConn.setUseCaches(false); // 忽略缓存
            httpConn.setRequestMethod("GET"); // 设置URL请求方法
            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String readLine;
            StringBuffer responseSb = new StringBuffer();
            while ((readLine = responseReader.readLine()) != null) {
                responseSb.append(readLine);
            }
            return responseSb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }
	/**
	 * 
	 * 
	 */
	
}
