package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Service
public class OrderServiceImpl implements OrderService
{
    @Resource
    OrderModelMapper orderModelMapper;
//分页查询所有订单信息
    public JSONObject selectAll(int pageNo, int pageSize)
    {
        int count=(pageNo-1)*pageSize;
        List<OrderModel> orderModels=orderModelMapper.selectAll(count,pageSize);
        int num=orderModelMapper.selectCount();
        int pageTotal=num/pageSize+1;
        JSONObject jObj=new JSONObject();
        jObj.put("pageTotal",pageTotal);
        jObj.put("orderModels",orderModels);
        jObj.put("pageNo",pageNo);
        return jObj;
    }
//根据订单号精确查询订单
    public OrderModel selectByOid(String oid) {
        OrderModel orderModel=orderModelMapper.selectByOid(oid);
        return orderModel;
    }
    //分条件查询，分页，模糊查询
    /*
    * 1：按订单号查询
    * 2：按渠道订单号查询
    * 3：按订单状态查询
    * 4：按支付方式查询
    * 5：按物流公司查询
    * 6：按省查询
    * 7：按市查询
    * 8：按区查询
    * 9：按收货人手机号查询
    */
    public JSONObject selects(int queryMode,int pageNo,int pageSize,String data)
    {
        Page page=new Page(pageNo,pageSize);
        int count=page.getStartPos();
        int pageTotal=0;
        List<OrderModel> orderModels=null;
        if(queryMode==1)
        {
            int num=orderModelMapper.CountByOid(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByOids(count,pageSize,data);
        }
        else if (queryMode==2)
        {
            int num=orderModelMapper.CountByChanneloid(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByChanneloids(count,pageSize,data);
        }
        else if (queryMode==3)
        {
            int num=orderModelMapper.CountByOrderStatus(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByOrderStatuss(count,pageSize,data);
        }
        else if (queryMode==4)
        {
            int num=orderModelMapper.CountByPayStyle(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByPayStyles(count,pageSize,data);
        }
        else if (queryMode==5)
        {
            int num=orderModelMapper.CountByLogisticsCompany(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByLogisticsCompanys(count,pageSize,data);
        }
        else if (queryMode==6)
        {
            int num=orderModelMapper.CountByReceiverProvince(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverProvinces(count,pageSize,data);
        }
        else if (queryMode==7)
        {
            int num=orderModelMapper.CountByReceiverCity(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverCitys(count,pageSize,data);
        }
        else if (queryMode==8)
        {
            int num=orderModelMapper.CountByReceiverArea(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverAreas(count,pageSize,data);
        }
        else if (queryMode==9)
        {
            int num=orderModelMapper.CountByReceiverMobel(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverMobels(count,pageSize,data);
        }
        JSONObject jObj=new JSONObject();
        jObj.put("pageTotal",pageTotal);
        jObj.put("pageNo",pageNo);
        jObj.put("orderModels",orderModels);
        return jObj;
    }
    public int updateByOidSelective(OrderModel record) {
        return orderModelMapper.updateByOidSelective(record);
    }
}
