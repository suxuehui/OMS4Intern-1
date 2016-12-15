package com.arvato.oms.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by ZHAN545 on 2016/12/12.
 */

public class HTTPClientDemo {
    private String url;
    public HTTPClientDemo(){}
    public HTTPClientDemo(String url)
    {
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String postMethod(String string)
    {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            try {
                StringEntity s = new StringEntity(string, "utf-8");
                s.setContentType("application/json");//发送json数据需要设置contentType
                post.setEntity(s);
                CloseableHttpResponse response = httpclient.execute(post);
                try {
                    HttpEntity entity = response.getEntity();
                    String str=EntityUtils.toString(entity);
                    return str;
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
