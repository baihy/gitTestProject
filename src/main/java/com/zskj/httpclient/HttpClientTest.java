package com.zskj.httpclient;

import java.util.HashMap;
import java.util.Map;

import com.zskj.utils.HttpClientUtils;

/**
 * Hello world!
 */
public class HttpClientTest {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/v1/hello/123";
		Map<String, String> paramMap = new HashMap<>();
		for (int i = 0; i < 2; i++) {
			String result = HttpClientUtils.post(url, paramMap);
			System.out.println(Thread.currentThread().getName() + "：" + result);
		}
	}
}
