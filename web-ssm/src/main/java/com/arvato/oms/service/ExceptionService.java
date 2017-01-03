package com.arvato.oms.service;

import com.arvato.oms.model.ExceptionModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/11.
 */
public interface ExceptionService {

    //分页显示订单列表
    String showExceptionOrder(HttpServletRequest request);

    //根据订单号删除所选异常订单
    List<ExceptionModel> deleteByOid(String  oId);

    //根据订单号查询该条异常订单记录
    ExceptionModel  selectByOid(String oId);

    //子页面显示
    String listExceptionSon(HttpServletRequest request);

    //根据订单号查询该条订单的异常类型
    String selectTypeByOid(String oId);

    int insertSelective(ExceptionModel exceptionModel);

}
