/**
 * @项目名称: httpclient
 * @文件名称: HttpClientUtils.java
 * @日期: 2018年11月23日
 * @版权: 2018 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author baihuayang
 */
public class HttpClientUtils {

    private static CloseableHttpClient httpclient;

    static {
        httpclient = HttpClients.createDefault();
    }

    private HttpClientUtils() {
        super();
    }

    /**
     * 处理get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpclient, httpGet);
    }

    /**
     * 处理post请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String post(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(handleParam(paramMap));
        return execute(httpclient, httpPost);
    }

    /**
     * 处理put请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String put(String url, Map<String, String> paramMap) {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(handleParam(paramMap));
        return execute(httpclient, httpPut);
    }

    /**
     * 处理delete请求
     *
     * @param url
     * @return
     */
    public static String delete(String url) {
        HttpDelete httpDelete = new HttpDelete(url);
        return execute(httpclient, httpDelete);
    }

    /**
     * 处理patch请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String patch(String url, Map<String, String> paramMap) {
        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setEntity(handleParam(paramMap));
        return execute(httpclient, httpPatch);
    }

    /**
     * 处理响应结果
     *
     * @param httpclient
     * @param httpRequestBase
     * @return
     */
    private static String execute(CloseableHttpClient httpclient, HttpRequestBase httpRequestBase) {
        String responseResult = null;
        try {
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            responseResult = httpclient.execute(httpRequestBase, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    /**
     * 封装请求参数
     *
     * @param paramMap
     * @return
     */
    private static UrlEncodedFormEntity handleParam(Map<String, String> paramMap) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null && paramMap.size() > 0) {
            for (Iterator<Entry<String, String>> it = paramMap.entrySet().iterator(); it.hasNext(); ) {
                Entry<String, String> entry = it.next();
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try {
            return new UrlEncodedFormEntity(nvps);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
