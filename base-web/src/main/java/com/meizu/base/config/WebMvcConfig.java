package com.meizu.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meizu.base.interceptor.WebInterceptor;

/**
 * Web Mvc 配置
 * 
 * 跨域，拦截器
 * 
 * @author xiedongxiao
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// "POST", "GET", "PUT", "OPTIONS", "DELETE"
		// spring-boot-2.3.9.RELEASE-
//		registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "DELETE").maxAge(3600).allowCredentials(true);
		// spring-boot-2.4.0+
		registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("POST", "GET", "PUT", "DELETE").maxAge(3600).allowCredentials(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册自己的拦截器并设置拦截的请求路径
		registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
