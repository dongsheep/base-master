package com.meizu.base.util;

import com.meizu.base.constant.StatusCode;
import com.meizu.base.dto.ResultDto;

/**
 * 响应工具类
 * 
 * @author xiedongxiao
 *
 */

public class ResponseUtil {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	public static ResultDto<String> ok() {
		ResultDto<String> result = new ResultDto<>();
		result.setCode(StatusCode.SUCCESS.getCode());
		result.setMsg(StatusCode.SUCCESS.getMsg());
		result.setData(SUCCESS);
		return result;
	}

	public static <T> ResultDto<T> ok(T obj) {
		ResultDto<T> result = new ResultDto<>();
		result.setCode(StatusCode.SUCCESS.getCode());
		result.setMsg(StatusCode.SUCCESS.getMsg());
		result.setData(obj);
		return result;
	}
	
	public static ResultDto<String> error(StatusCode obj) {
		ResultDto<String> result = new ResultDto<>();
		result.setCode(obj.getCode());
		result.setMsg(obj.getMsg());
		result.setData(ERROR);
		return result;
	}
	
	public static ResultDto<String> error(String code, String msg) {
		ResultDto<String> result = new ResultDto<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ERROR);
		return result;
	}

}
