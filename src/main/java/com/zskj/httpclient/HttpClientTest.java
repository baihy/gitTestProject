package com.zskj.httpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.zskj.utils.HttpClientUtils;

/**
 * Hello world!
 */
public class HttpClientTest {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/hello/123";
		Map<String, String> paramMap = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(new Random().nextInt(1000) + 500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String result = HttpClientUtils.post(url, paramMap);
				System.out.println(Thread.currentThread().getName() + "：" + result);
			}).start();
			System.out.println(i);
		}
	}
}
