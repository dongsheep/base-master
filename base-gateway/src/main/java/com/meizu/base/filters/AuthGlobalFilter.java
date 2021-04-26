package com.meizu.base.filters;

import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 */

//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
	/**
	 * 逻辑判断
	 *
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		if (!StringUtils.equals("admin", token)) {
			System.err.println("认证失败");
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	/**
	 * 当前过滤器的优先级，数字越小，优先度越高
	 *
	 * @return
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
