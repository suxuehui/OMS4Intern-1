package com.arvato.oms.utils;

import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.OutboundorderModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by GONG036 on 2016/12/25.
 */
public class ObjectToJsonstr {
    //将对象转json转字符串
    public String outobjtojson(Page pagelist, List<OutboundorderModel> list, String jsonstr){
        JSONObject json = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }
    //将对象转json转字符串
    public String objtojson(Page pagelist, List<InboundorderModel> list, String jsonstr){
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }
}
