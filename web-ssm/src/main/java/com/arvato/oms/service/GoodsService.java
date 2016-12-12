package com.arvato.oms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
public interface GoodsService {
    public JSONObject selectByOid(int pageNo, int pageSize, String oId);
}
