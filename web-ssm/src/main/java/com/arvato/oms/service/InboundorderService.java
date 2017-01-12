package com.arvato.oms.service;

import com.arvato.oms.model.InboundorderModel;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by GONG036 on 2016/12/8.
 */
public interface InboundorderService {

     String inboundsearchAllByparam(HttpServletRequest request) throws UnsupportedEncodingException;

     InboundorderModel  selectByOid(String oid);

     //更新入库单列表
     int updateByInboundId(String inboundid, String inboundstate,Date modifytime,String modifyman);
     //inbounderdetail
}
