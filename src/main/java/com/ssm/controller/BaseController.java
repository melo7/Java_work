package com.ssm.controller;

import com.ssm.utils.Result;
import com.ssm.utils.StringEscapeEditor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

/**
 * @description：基础 controller
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }


    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
    
    /**
     * 发送当个json对象
     */
    
    public void sendJSONObject(Object obj,HttpServletResponse response,String... params){
		JsonConfig config = new JsonConfig();
		config.setExcludes(params);
		String json = JSONObject.fromObject(obj,config).toString();
		System.out.println("json"+json);
		sendMsg(json, response);
	}
    
    /**
	 * 发送json 数组
	 * @param obj
	 * @param response
	 * @param params
	 */
	public void sendJSONArray(Object obj,HttpServletResponse response,String... params){
		JsonConfig config = new JsonConfig();
		config.setExcludes(params);
		config.setIgnoreDefaultExcludes(false); //设置默认忽略
		String json = JSONArray.fromObject(obj,config).toString();
		System.out.println("json"+json);
		sendMsg(json, response);
	}
    
    
    public void sendMsg(Object obj,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null ; 
		try {
			out = response.getWriter();
			out.print(obj);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
	}
    
}
