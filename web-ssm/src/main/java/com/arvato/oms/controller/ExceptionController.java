package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.*;
import com.arvato.oms.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZHOU169 on 2016/12/11.
 */


@Controller
@RequestMapping("/exceptionOrder/")
public class ExceptionController {
    private Logger log = Logger.getLogger(ExceptionController.class);

    private static final String PAGE = "OMSPage";
    @Resource
    private ExceptionService exceptionServiceImpl;
    @Resource
    private RelationogService relationogServiceImpl;
    @Resource
    private GoodsModelService goodsServiceImpl;
    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private OutboundorderService outboundorderServiceImpl;

    private static final String EXCEPTION="exception";

    //进入页面
    @RequestMapping(value="indexExceptionList")
    public String indexExceptionList(){
        return PAGE;
    }

    //分页显示出所有异常订单
    @RequestMapping(value = "showExceptionList")
    @ResponseBody
    public String showExceptionList(HttpServletRequest request,HttpServletResponse response ){

        return  exceptionServiceImpl.showExceptionOrder(request );
    }

    //取消异常订单
    @RequestMapping(value = "cancelException")
    @ResponseBody
    public String cancelException(HttpServletRequest request,HttpSession session){
        String oid = request.getParameter("oid");
        String[] sq=oid.split(",");
        String userName2 = (String)session.getAttribute("uname");
        for (int i = 0; i < sq.length; i++) {
            exceptionServiceImpl.deleteByOid(sq[i]);
            String orderStatus1 ="待预检";
            //先将订单状态改为“待预检”,然后才能进行订单的修改操作
            orderServiceImpl.updateOrder2(orderStatus1,new Date(),userName2,sq[i]);
            //再删除订单页面的订单信息
            orderServiceImpl.cancleOrder(sq[i],userName2);
            return "{\"msg\":\"2\"}";
        }
        return PAGE;
    }

    //异常订单的处理
    @RequestMapping(value = "handleException")
    @ResponseBody
    public String handleException(HttpServletRequest request,HttpSession session){
        Set set=new HashSet();
        String userName = (String)session.getAttribute("uname");
        String oid2 =request.getParameter("oid4");
        String[] exOid=oid2.split(",");
        for(int i=0;i<exOid.length;i++){
            //根据订单号查询该条订单的异常类型
            String exceptionTypeList=exceptionServiceImpl.selectTypeByOid(exOid[i]);
            set.add(exceptionTypeList);
        }
        if(set.size()==1){//判断异常类型是否相同
            Iterator iterator=set.iterator();
            while(iterator.hasNext()){
                String exceptionType = iterator.next().toString();
                //由预检发送过来，处理方式为：取消订单
                if ("商品异常".equals(exceptionType)){
                    for(int j=0;j<exOid.length;j++){
                        //先删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        String orderStatus1 ="待预检";
                        //先将订单状态改为“待预检”,然后才能进行订单的修改操作
                        orderServiceImpl.updateOrder2(orderStatus1,new Date(),userName,exOid[j]);
                        //再删除订单页面的订单信息
                        orderServiceImpl.cancleOrder(exOid[j],userName);
                        return "{\"msg\":\"2\"}";
                    }
                }
                //由预检发送过来，处理方式为：取消订单
                if("库存异常".equals(exceptionType))
                {
                    for(int j=0;j<exOid.length;j++){
                        //先删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        String orderStatus2 ="待预检";
                        //先将订单状态改为“待预检”,然后才能进行订单的修改操作
                        orderServiceImpl.updateOrder2(orderStatus2,new Date(),userName,exOid[j]);
                        //再删除订单页面的订单信息
                        orderServiceImpl.cancleOrder(exOid[j],userName);
                        return "{\"msg\":\"2\"}";
                    }
                }
                //由预检发送过来，处理方式为：跟客户确认后，进行下一步备注异常的检验
                if("金额异常".equals(exceptionType))
                {
                    for(int j=0;j<exOid.length;j++){
                        //再删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        String orderStatus ="待预检";
                        //先将订单状态改为“待预检”
                        orderServiceImpl.updateOrder2(orderStatus,new Date(),userName,exOid[j]);
                        String exceptionType2 = "备注异常";
                        //进行下一步备注异常的检验
                        int k=orderServiceImpl.previewOrder(exOid[j],exceptionType2,userName);
                        int exception = 0;
                        if(k==1)
                        {
                            String orderStatus3 ="待路由";
                            //先将订单状态改为“待路由”,然后才能进行订单的修改操作
                            orderServiceImpl.updateOrder2(orderStatus3,new Date(),userName,exOid[j]);
                        }
                        return "{\"msg\":\"2\"}";
                    }
                }

                //由预检发送过来，处理方式为：确认备注信息后，进行路由
                if("备注异常".equals(exceptionType))
                {
                    for(int j=0;j<exOid.length;j++){
                        String orderStatus4 ="待路由";
                        //先将订单状态改为“待路由”,然后才能进行订单的修改操作
                        orderServiceImpl.updateOrder2(orderStatus4,new Date(),userName,exOid[j]);
                        //再删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        return "{\"msg\":\"2\"}";
                    }
                }
                //向wms发送出库单是异常，处理方式为：再次将出库单信息发送给WMS
                if("出库异常".equals(exceptionType))
                {
                    for(int j=0;j<exOid.length;j++) {
                        //根据订单号查询出该条订单记录
                        OrderModel orderModel=orderServiceImpl.selectByOid(exOid[j]);
                        //根据订单号查询出该条出库单记录
                        OutboundorderModel outboundorderModel =outboundorderServiceImpl.selectByOid(exOid[j]);
                        //再次将出库单信息发送给WMS
                        int s = orderServiceImpl.sendOutboundOrder(orderModel,outboundorderModel,userName);
                        if (s==1){
                            String orderStatus5 ="待出库";
                            //先将订单状态改为“待出库”,然后才能进行订单的修改操作
                            orderServiceImpl.updateOrder2(orderStatus5,new Date(),userName,exOid[j]);
                            //再删除异常页面的异常订单
                            exceptionServiceImpl.deleteByOid(exOid[j]);
                            return "{\"msg\":\"3\"}";
                        }
                        if (s==5){
                            return "{\"msg\":\"4\"}";
                        }
                    }
                }
                //wms发送过来缺货，处理方式为：取消订单
                if("仓库库存异常".equals(exceptionType)){
                    for(int j=0;j<exOid.length;j++){
                        //先删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        String orderStatus6 ="待预检";
                        //先将订单状态改为“待预检”,然后才能进行订单的修改操作
                        orderServiceImpl.updateOrder2(orderStatus6,new Date(),userName,exOid[j]);
                        //再删除订单页面的订单信息
                        orderServiceImpl.cancleOrder(exOid[j],userName);
                        return "{\"msg\":\"2\"}";
                    }
                }
            }
        }else{
            log.info("异常类型不完全相同");
            return "{\"msg\":\"1\"}";
        }
        return PAGE;
    }

    //子页面显示
    @RequestMapping(value="listExceptionSon")
    @ResponseBody
    public String listExceptionSon(HttpServletRequest request )
    {
        return exceptionServiceImpl.listExceptionSon(request);
    }

    //异常订单详细页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        String oid3=request.getParameter("oid3");
        //查询异常订单列表
        ExceptionModel exceptionList = exceptionServiceImpl.selectByOid(oid3);
        // 获取商品编码  查询关系表
        List<RelationogModel> rogList= relationogServiceImpl.selectALLByOid(oid3);
        //获取商品实体 查询商品表
        List<Object> godsList=new ArrayList<Object>();
        for(int i=0;i<rogList.size();i++){
            GoodsPojo gp=new GoodsPojo();
            //获取商品编号
            String sno= rogList.get(i).getGoodsno();
            //获取商品数量
            int snum= rogList.get(i).getGoodnum() ;
            //获取单个商品折扣后的价格
            BigDecimal divideorderfee = rogList.get(i).getDivideorderfee();
            GoodsModel gm=goodsServiceImpl.selectByGoodsNo(sno);
            gp.setGoodsnum(snum);
            gp.setGoodsname(gm.getGoodsname());
            gp.setGoodsno(gm.getGoodsno());
            gp.setGoodsprice(gm.getGoodsprice());
            gp.setDivideorderfee(divideorderfee);
            godsList.add(gp);
        }
        model.addAttribute("gods",godsList);
        model.addAttribute("exceptionList",exceptionList);
        model.addAttribute("rog",rogList);
        model.addAttribute("oid",oid3);

        return "exceptionDetails";
    }


}
