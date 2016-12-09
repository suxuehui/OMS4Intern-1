package com.arvato.oms.service;

import com.arvato.oms.model.OutboundorderModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/6.
 */
public interface OutboundorderService {

    //条件模糊查询
    Model searchAllByparam(HttpServletRequest request, Model model) throws UnsupportedEncodingException;

    //精确获取信息by oid
    List<OutboundorderModel> selectByOid(String oid);

}




