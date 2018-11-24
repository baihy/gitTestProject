package com.zskj.httpclient;

import com.zskj.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 */
public class HttpClientTest {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/v1/hello/123";
		Map<String, String> paramMap = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			String result = HttpClientUtils.post(url, paramMap);
			System.out.println(Thread.currentThread().getName() + "：" + result);
		}
	}
}
