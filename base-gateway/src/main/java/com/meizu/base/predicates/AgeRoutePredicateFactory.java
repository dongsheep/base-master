package com.meizu.base.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.nacos.common.utils.StringUtils;

/**
 * 自定义路由断言工厂类(自定义局部过滤器类似)
 * <p>
 * 1.名字必须是*RoutePredicateFactory 2.必须继承AbstractRoutePredicateFactory
 */

//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

	public AgeRoutePredicateFactory() {
		super(AgeRoutePredicateFactory.Config.class);
	}

	/**
	 * 读取配置文件中的参数值，赋值到配置类的属性上
	 *
	 * @return
	 */
	@Override
	public List<String> shortcutFieldOrder() {
		// 顺序必须跟配置文件中的值的顺序一样
		return Arrays.asList("minAge", "maxAge");
	}

	/**
	 * 断言逻辑
	 *
	 * @param config
	 * @return
	 */
	@Override
	public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
		return new Predicate<ServerWebExchange>() {
			@Override
			public boolean test(ServerWebExchange serverWebExchange) {
				// 接收参数
				String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
				if (StringUtils.isNotEmpty(ageStr)) {
					int age = Integer.parseInt(ageStr);
					if (age >= config.getMinAge() && age <= config.getMaxAge()) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}
		};
	}

	/**
	 * 配置类，对应yml文件中对应的参数
	 */
	public static class Config {
		private int minAge;
		private int maxAge;

		public int getMinAge() {
			return minAge;
		}

		public void setMinAge(int minAge) {
			this.minAge = minAge;
		}

		public int getMaxAge() {
			return maxAge;
		}

		public void setMaxAge(int maxAge) {
			this.maxAge = maxAge;
		}

		public Config() {
		}
	}

}
