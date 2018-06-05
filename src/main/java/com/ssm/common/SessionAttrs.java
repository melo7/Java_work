package com.ssm.common;

import javax.servlet.http.HttpSession;

/**
 * 会话属性键
 * <p>
 * 注：因为涉及到会话属性可能被覆盖，这里所以的key应该保证全局唯一。
 * 
 * @author 
 * @date 2014-11-20 下午2:02:11
 */
public final class SessionAttrs {

	/** photo相关 */
	public enum Advertising {
		PHOTO;
		
		/**
		 * 生成会话属性key
		 * 
		 * @param session
		 */
		public String getKey() {
			StringBuilder sb = new StringBuilder();
			sb.append(this.getClass().getSimpleName());
			sb.append("_");
			sb.append(this.name());
			return sb.toString();
		}

		/**
		 * 获取会话属性val
		 * 
		 * @param session
		 */
		@SuppressWarnings("unchecked")
		public <T> T getVal(HttpSession session, T clazz) {
			Object o = session.getAttribute(this.getKey());
			if (o == null) {
				if (clazz.getClass() == String.class) {
					return (T) "";
				} else {
					return null;
				}
			} else {// 只限同类
				if (clazz.getClass() == o.getClass()) {
					return (T) o;
				} else {
					return null;
				}
			}
		}

		/**
		 * 清理会话
		 * 
		 * @param session
		 */
		public static void clear(HttpSession session) {
			Advertising[] values = Advertising.values();
			for (Advertising value : values) {
				session.removeAttribute(value.getKey());
			}
		}
	}

	
}
