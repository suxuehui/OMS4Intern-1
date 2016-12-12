package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/6.
 */
public interface OutboundorderService {

    //条件模糊查询
     String searchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException;

    //精确获取信息by oid
     OutboundorderModel selectByOid(String oid);

}




