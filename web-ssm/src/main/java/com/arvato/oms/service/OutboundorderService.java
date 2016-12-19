package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public interface OutboundorderService
{
     public JSONObject outboundOrder(String oId);


    //出库单条件查询，
    String searchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException;

    //出库单子页面查询，
    String listSonPage(HttpServletRequest request);

    //通过oid获取一条信息
    OutboundorderModel selectByOid(String oid);
}
