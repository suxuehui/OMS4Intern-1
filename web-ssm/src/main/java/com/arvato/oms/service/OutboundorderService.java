package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
public interface OutboundorderService
{
    //public JSONObject outboundOrder(String oId);


    /**
     * Created by Gong on 2016/12/12.
     * 出库单条件查询，
     * 子页面查询，
     * 通过oid获取一条信息
     */
    String searchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException;

    String listSonPage(HttpServletRequest request);

    OutboundorderModel selectByOid(String oid);
}
