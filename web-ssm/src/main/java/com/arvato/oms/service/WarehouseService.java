package com.arvato.oms.service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/20.
 */

public interface WarehouseService {

    //列出所有资料
    String listAllByparam(HttpServletRequest request) throws UnsupportedEncodingException;



}
