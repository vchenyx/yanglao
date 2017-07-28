package com.common.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.service.HttpClient;
import com.common.util.JSONUtil;

@Service
public class HttpClientImpl implements HttpClient {

	// 创建Httpclient对象
		@Autowired(required = false)
		private CloseableHttpClient httpClient;

		// 请求信息的配置
		@Autowired(required = false)
		private RequestConfig requestConfig;

		/**
		 * 执行Get请求
		 * 
		 * @param url
		 * @return 请求到的内容
		 * @throws URISyntaxException
		 * @throws IOException
		 * @throws ClientProtocolException
		 */
		@Override
		public String doGet(String url) {
			return doGet(url, null);
		}

		/**
		 * 执行Get请求
		 * 
		 * @param url
		 * @param params
		 *            请求中的参数
		 * @return 请求到的内容
		 * @throws URISyntaxException
		 * @throws IOException
		 * @throws ClientProtocolException
		 */
		@Override
		public String doGet(String url, Map<String, Object> param) {
			try {

				// 创建http GET请求
				HttpGet httpGet = null;
				// 拼接参数
				String paramStr = "";
				if (!param.isEmpty()) {
					Set<Entry<String, Object>> entrySet = param.entrySet();
					for (Entry<String, Object> entry : entrySet) {
						paramStr += "&" + entry.getKey() + "=" + entry.getValue();
					}
					paramStr.substring(1);
				}
				if (!"".equals(paramStr)) {
					url += "?" + paramStr;
				}
				httpGet = new HttpGet(url);
				// 设置请求参数
				httpGet.setConfig(this.requestConfig);
				// 请求的结果
				CloseableHttpResponse response = null;
				try {
					// 执行请求
					response = httpClient.execute(httpGet);
					// 判断返回状态是否为200
					if (response.getStatusLine().getStatusCode() == 200) {
						// 获取服务端返回的数据,并返回
						return EntityUtils.toString(response.getEntity(), "UTF-8");
					}
				} finally {
					if (response != null) {
						response.close();
					}
				}
			} catch (Exception e) {
				
			}
			return null;
		}

		/**
		 * 
		 * @param url
		 * @param params
		 *            请求中的参数
		 * @return 请求到的内容
		 * @throws URISyntaxException
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		@Override
		public String doPost(String url, Map<String, Object> params) {
			try {
				// 构造一个form表单式的实体
				StringEntity strEntity = null;
				// 定义请求的参数
				if (params != null) {
					String str = JSONUtil.beanToStr(params);
					strEntity = new StringEntity(str, Charset.forName("UTF-8"));
				}
				// 创建http GET请求
				HttpPost httpPost = new HttpPost(url);
				if (strEntity != null) {
					// 将请求实体设置到httpPost对象中
					httpPost.setEntity(strEntity);
				}
				// 设置请求参数
				httpPost.setConfig(this.requestConfig);
				// 请求的结果
				CloseableHttpResponse response = null;
				try {
					// 执行请求
					response = httpClient.execute(httpPost);
					// 判断返回状态是否为200
					if (response.getStatusLine().getStatusCode() == 200) {
						// 获取服务端返回的数据,并返回
						return EntityUtils.toString(response.getEntity(), "UTF-8");
					}
				} finally {
					if (response != null) {
						response.close();
					}
				}
			} catch (Exception e) {
			}
			return null;
		}

		/**
		 * 
		 * @param url
		 * @param params
		 *            请求中的参数
		 * @return 请求到的内容
		 * @throws URISyntaxException
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		@Override
		public String doPost(String url) {
			return doPost(url, null);
		}
}
