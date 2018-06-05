package com.ssm.model.error;


import org.apache.log4j.Logger;




/**
 * 受检查异常基类
 * @author 
 * @date 2013-11-22 下午1:39:17
 */
public class BaseException extends Exception implements IExceptionLogger, IExceptionLevel {
	private static final long serialVersionUID = 1L;
	/** 异常日志级别 ，默认为error级别 */
	private ExceptionLevel level = ExceptionLevel.ERROR;

	public BaseException() {
	}

	public BaseException(ExceptionLevel level) {
		this.level = level;
	}
	
	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
	public BaseException(ExceptionLevel level, String message) {
		super(message);
		this.level = level;
	}

	public BaseException(ExceptionLevel level, Throwable cause) {
		super(cause);
		this.level = level;
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(ExceptionLevel level, String message, Throwable cause) {
		super(message, cause);
		this.level = level;
	}
	
	@Override
	public ExceptionLevel getLevel() {
		return level;
	}

	public void setLevel(ExceptionLevel level) {
		this.level = level;
	}

	@Override
	public void writeTo(Logger logger) {
		switch (level) {
		case WARN:
			logger.warn(getMessage(), getCause());
			break;
		case FATAL:
			logger.fatal(getMessage(), getCause());
			break;
		default:
			//默认为error级别
			logger.error(getMessage(), getCause());
		}
	}

}
