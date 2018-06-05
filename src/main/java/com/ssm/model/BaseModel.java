package com.ssm.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 数据模型基础类
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-21 下午2:15:15
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
