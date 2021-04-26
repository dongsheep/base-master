package com.meizu.base.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meizu.base.dto.ResultDto;
import com.meizu.base.exception.BusinessException;
import com.meizu.base.util.ResponseUtil;

/**
 * 全局异常处理
 * 
 * @author xiedongxiao
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = BusinessException.class)
	public ResultDto<String> bussinessExceptionHandler(BusinessException e) {
		log.error("BussinessException error..." + e.getMsg());
		return ResponseUtil.error(e.getCode(), e.getMsg());
	}

//	@ExceptionHandler(value = Exception.class)
//	public ResultDto<String> exceptionHandler(Exception e) {
//		log.error("Exception error...", e);
//		return ResponseUtil.error(StatusCode.INTERNAL_ERROR.getCode(), e.getMessage());
//	}

}
