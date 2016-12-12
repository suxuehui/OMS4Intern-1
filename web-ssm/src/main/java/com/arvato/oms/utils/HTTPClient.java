package com.arvato.oms.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.extractor.ExcelExtractor;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public class HTTPClient {
    private String url;
    public HTTPClient(){}
    public HTTPClient(String url)
    {
        this.url=url;
    }
    public JSONObject postMethod(JSONObject jsonObject)
    {
        CloseableHttpClient httpclient=HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try{
            StringEntity s=new StringEntity(jsonObject.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            CloseableHttpResponse response=httpclient.execute(post);
        }catch ()
    }
}
