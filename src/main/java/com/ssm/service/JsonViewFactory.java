package com.ssm.service;

import org.springframework.web.servlet.ModelAndView;

import com.ssm.model.ResponseResult;



/**
 * JSON视图工厂类，用于构建控制层响应的JSON视图对象
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-21 下午9:05:16
 */
public class JsonViewFactory {
	/** 视图名称-固定值jsonView */
	public static final String VIEW_NAME = "jsonView";
	/** 模型数据的Key值，固定值responseResult */
	public static final String MODEL_KEY = "responseResult";
	
	/**
	 * 构建JSON视图对象
	 * @param <T>            响应数据类型参数
	 * @param responseResult 响应结果
	 * @return ModelAndView
	 */
	public static <T> ModelAndView buildJsonView(ResponseResult<T> responseResult) {
		return buildJsonView(new ModelAndView(), responseResult);
	}
	
	/**
	 * 构建JSON视图对象，用于请求成功、且带响应数据
	 * @param <T>  响应数据类型参数
	 * @param data 响应数据
	 * @return ModelAndView
	 */
	public static <T> ModelAndView buildJsonView(T data) {
    	return buildJsonView(new ModelAndView(), new ResponseResult<T>(data));
	}
	
	/**
	 * 构建成功JSON视图
	 * @param message 成功消息
	 * @return ModelAndView
	 */
	public static ModelAndView buildSuccessJsonView(String message) {
		return buildJsonView(new ResponseResult<Object>(ResponseResult.SUCCESS, message));
	}

	/**
	 * 构建失败JSON视图
	 * @param message 成功消息
	 * @return ModelAndView
	 */
	public static ModelAndView buildFailedJsonView(String message) {
		return buildJsonView(new ResponseResult<Object>(ResponseResult.FAILED, message));
	}
	
	/**
	 * 构建JSON视图对象
	 *
	 * @param <T>
	 * @param modelAndView   模型视图对象
	 * @param responseResult 响应结果
	 */
	private static <T> ModelAndView buildJsonView(ModelAndView modelAndView, ResponseResult<T> responseResult) {
		modelAndView.setViewName(VIEW_NAME);
    	modelAndView.addObject(MODEL_KEY, responseResult);
    	return modelAndView;
	}
	
	/**
	 * 构建原始JSON视图对象
	 * @param object 响应结果
	 * @return ModelAndView
	 */
	public static ModelAndView buildRawJsonView(Object object) {
		return buildRawJsonView(new ModelAndView(), object);
	}
	
	/**
	 * 构建原始JSON视图对象
	 * @param modelAndView   模型视图对象
	 * @param object 响应结果
	 */
	private static ModelAndView buildRawJsonView(ModelAndView modelAndView, Object object) {
		modelAndView.setViewName(VIEW_NAME);
    	modelAndView.addObject(MODEL_KEY, object);
    	return modelAndView;
	}
	
	
}
