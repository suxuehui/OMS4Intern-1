package com.arvato.oms.service;

import com.arvato.oms.model.ExceptionModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */
public interface RefoundOrderService {

    //分页显示订单列表
    String showRefoundOrderList(HttpServletRequest request);

}
