package com.meizu.base.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author xiedongxiao
 *
 */

public class LogUtil {

	public static Logger get(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}
	
	public static Logger get(Object value) {
		return LogManager.getLogger(value);
	}
	
	public static Logger get(String name) {
		return LogManager.getLogger(name);
	}
	
}
