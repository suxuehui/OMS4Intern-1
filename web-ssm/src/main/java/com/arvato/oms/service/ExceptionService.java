package com.arvato.oms.service;

import com.arvato.oms.model.ExceptionModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHOU169 on 2016/12/6.
 */
public interface ExceptionService {

    //分页显示订单列表
    void showExceptionOrder(HttpServletRequest request, Model model);

    //通过订单号查询
    List<ExceptionModel> selectByOid(String oId);

    //通过渠道订单号查询
    List<ExceptionModel> selectByChannelOid(String channelOid);

    //通过异常状态查询
    List<ExceptionModel> selectByExceptionStatus(String exceptionStatus);

    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionModel record);

    int insertSelective(ExceptionModel record);

    ExceptionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionModel record);

    int updateByPrimaryKey(ExceptionModel record);
}
