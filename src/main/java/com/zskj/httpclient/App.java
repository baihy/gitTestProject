package com.zskj.httpclient;

import com.zskj.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/v1/hello/put";
        Map<String, String> paramMap = new HashMap<>();
        // String result = HttpClientUtils.post(url, paramMap);
        paramMap.put("id", "abc");
        String result = HttpClientUtils.put(url, paramMap);
        System.out.println("返回结果：" + result);
    }
}
