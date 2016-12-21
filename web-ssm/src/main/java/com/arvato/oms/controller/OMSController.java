package com.arvato.oms.controller;

import com.arvato.oms.service.impl.InboundorderServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GONG036 on 2016/12/14.
 */
@Controller
@RequestMapping("OMS")
public class OMSController {
    @Resource
    private InboundorderServiceImpl inboundorderserviceimpl;
    //进入主页面
    @RequestMapping(value = "home")
    public String listseach() {
        return "OMSPage";
    }

    //接受wms传来的数据，更新入库单
    @RequestMapping(value = "updateInboundOrder")
    @ResponseBody
    public String updateInboundOrder(@RequestBody String message) {
        int s = 0;
        try {
            JSONObject object = JSONObject.fromObject(message);//获得从client传来的json对象
            String status_codes = object.getJSONObject("inbound_update_message").getString("status_codes"); //获得状态码
            JSONObject inbounds = object.getJSONObject("inbound_update_message").getJSONObject("inbounds"); //获得入库更新信息数组
            JSONArray inbound = inbounds.getJSONArray("inbound");
            Map<String, Object> map = new HashMap<String, Object>();
            int code = Integer.parseInt(status_codes);
            if (code == 1) {
                //向数据库写入数据
                for (int i = 0; i < inbound.size(); i++) {
                    String inboundId = inbound.getJSONObject(i).getString("inboundId");
                    String inboundState = inbound.getJSONObject(i).getString("inboundState");
                    s = inboundorderserviceimpl.updateByInboundId(inboundId, inboundState);
                    if (s == 0) {
                        return "{\"status_codes\":000,\"msg\":\"参数的数据格式有误\",\"body\":\"入库单更新失败\"}";
                    }
                }
            }
        } catch (Exception e) {//异常的捕获
            return "{\"status_codes\":111,\"msg\":\"json字符串格式有误\",\"body\":\"入库单数据参数接收失败\"}";
        }
        return "{\"status_codes\":222,\"msg\":\"入库单状态更新成功\",\"body\":\"入库单状态更新成功\"}";
    }
}