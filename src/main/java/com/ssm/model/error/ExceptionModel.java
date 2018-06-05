package com.ssm.model.error;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 封装异常模型，用于将传递给异常处理html页面进行显示 
 * @author luoxm@zjhcsoft.com 
 * @date 2013-11-22 下午1:19:05
 */
public class ExceptionModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	/**异常模型对象在ModelAndView中对应的属性名称*/
	public final static String EXCEPTION_MODEL_OBJECT_NAME = "exceptionModel";
	/**异常类名 */
    private String exceptionClassName;
    /**异常堆栈*/
    private String stacktrace;
    /**异常消息*/
    private String message;
    /**异常原因(异常根源)*/
    private String cause;
    
    /**
     * 构造方法，将Exception转化为异常模型
     */
	public ExceptionModel(Exception e) {
		if (e == null)
			return;
		exceptionClassName = e.getClass().getName();
		message = e.getMessage();
		cause = e.toString();
		StringBuilder buildTrace = new StringBuilder();
		appendStackTrace(e,buildTrace);
		//加入换行
		stacktrace = StringUtils.replace(buildTrace.toString(), ")", ")\r\n");
	}
	
	/**
	 * 递归获取深层次异常信息
	 * @param e
	 * @param buildTrace
	 * @throws
	 */
	private void appendStackTrace(Throwable e,StringBuilder buildTrace){
		Throwable causeException = e.getCause();
		if(causeException != null){
			cause = causeException.toString();
			appendStackTrace(causeException,buildTrace);
		}
		else{
			buildTrace.append("Caused by: ");
			StackTraceElement[] stes = e.getStackTrace();
			if (stes != null && stes.length > 0) {
				for (StackTraceElement ste : stes) {
					buildTrace.append(ste.toString());
				}
			}
		}
	}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionClassName() {
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}

	public String getStacktrace() {
		return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
    
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}