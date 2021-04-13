package com.meizu.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

/**
 * Http请求工具类
 * 
 * @author xiedongxiao
 *
 */

public class HttpUtil {

	private static Logger log = LogUtil.get(HttpUtil.class);

	/**
	 * HttpClient的get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		try {
			// 创建httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			// 创建get方式请求对象
			HttpGet get = new HttpGet(url);
			Builder config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000);
			get.setConfig(config.build());
			// 执行请求
			CloseableHttpResponse response = client.execute(get);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			String reuslt = null;
			if (entity != null) {
				reuslt = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			return reuslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * HttpClient的post请求-@RequestParam
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static String postByUrl(String url, Map<String, String> map) {
		try {
			// 创建httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			// 创建post方式请求对象
			HttpPost post = new HttpPost();
			Builder config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000);
			post.setConfig(config.build());
			// 封装参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (!CollectionUtils.isEmpty(map)) {
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			// 设置参数到请求对象中
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			// 执行请求
			CloseableHttpResponse response = client.execute(post);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			String reuslt = null;
			if (entity != null) {
				reuslt = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			return reuslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * HttpClient的post请求-@RequestBody
	 * 
	 * @param url
	 * @param bodyStr
	 * @return
	 * @throws Exception
	 */
	public static String postByBody(String url, String bodyStr) {
		try {
			// 创建httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			// 创建post方式请求对象
			HttpPost post = new HttpPost(url);
			Builder config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000);
			post.setConfig(config.build());
			// 用RequestBody接参数
			StringEntity stringEntity = new StringEntity(bodyStr);
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			post.setEntity(stringEntity);
			// 执行请求
			CloseableHttpResponse response = client.execute(post);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			String reuslt = null;
			if (entity != null) {
				reuslt = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			return reuslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * HttpClient的post请求-@RequestBody
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static byte[] postByBodyForByte(String url, String bodyStr) {
		try {
			// 创建httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			// 创建post方式请求对象
			HttpPost post = new HttpPost(url);
			Builder config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000);
			post.setConfig(config.build());
			// 用RequestBody接参数
			StringEntity stringEntity = new StringEntity(bodyStr);
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			post.setEntity(stringEntity);
			// 执行请求
			CloseableHttpResponse response = client.execute(post);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			byte[] reuslt = null;
			if (entity != null) {
				reuslt = EntityUtils.toByteArray(entity);
			}
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			return reuslt;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

}
