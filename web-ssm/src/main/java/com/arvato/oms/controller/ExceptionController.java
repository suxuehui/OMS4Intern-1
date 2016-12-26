package com.arvato.oms.controller;

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
import java.util.*;

/**
 * Created by ZHOU169 on 2016/12/11.
 */


@Controller
@RequestMapping("/exceptionOrder/")
public class ExceptionController {

    private Logger log = Logger.getLogger(ExceptionController.class);

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

    //进入页面
    @RequestMapping(value="indexExceptionList")
    public String indexExceptionList(){
        return "OMSPage";
    }

    //分页显示出所有异常订单
    @RequestMapping(value = "showExceptionList")
    @ResponseBody
    public String showExceptionList(HttpServletRequest request,HttpServletResponse response ){

        String str = exceptionServiceImpl.showExceptionOrder(request );
        return  str;
    }

    //取消异常订单
    @RequestMapping(value = "cancelException")
    public String cancelException(HttpServletRequest request){
        String oid = request.getParameter("oid");
        System.out.println("zzzzzz:"+oid);
        String[] sq=oid.split(",");
        for (int i = 0; i < sq.length; i++) {
            exceptionServiceImpl.deleteByOid(sq[i]);
        }
        return "OMSPage";
    }

    //异常订单的处理
    @RequestMapping(value = "handleException")
    @ResponseBody
    public String handleExeption(HttpServletRequest request,HttpSession session,HttpServletResponse response){
        Set set=new HashSet();
        String userName = (String)session.getAttribute("uname");
        String oid2 =request.getParameter("oid2");
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
                if (exceptionType.equals("商品异常")){
                    for(int j=0;j<exOid.length;j++){
                        //先删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        //再删除订单页面的订单信息
                        orderServiceImpl.cancleOrder(exOid[j],userName);
                    }
                }
                //由预检发送过来，处理方式为：取消订单
                if(exceptionType.equals("仓库库存异常"))
                {
                    for(int j=0;j<exOid.length;j++){
                        //先删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                        //再删除订单页面的订单信息
                        orderServiceImpl.cancleOrder(exOid[j],userName);
                    }
                }
                //由预检发送过来，处理方式为：跟客户确认后，进行下一步备注异常的检验
                if(exceptionType.equals("金额异常"))
                {
                    for(int j=0;j<exOid.length;j++){
                        String orderStatus ="待预检";
                        //先将订单状态改为“待预检”
                        orderServiceImpl.updateOrder(orderStatus,exOid[j]);
                        String exceptionType2 = "备注异常";
                        //进行下一步备注异常的检验
                        orderServiceImpl.previewOrder(exOid[j],exceptionType2,userName);
                        //再删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                    }
                }

                //由预检发送过来，处理方式为：确认备注信息后，进行路由
                if(exceptionType.equals("备注异常"))
                {
                    for(int j=0;j<exOid.length;j++){
                        //进行路由
                        orderServiceImpl.routeOrder(exOid[j],userName);
                        //再删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                    }
                }
                //向wms发送出库单是异常，处理方式为：再次将出库单信息发送给WMS
                if(exceptionType.equals("出库异常"))
                {
                    for(int j=0;j<exOid.length;j++) {
                        //根据订单号查询出该条订单记录
                        OrderModel orderModel=orderServiceImpl.selectByOid(exOid[j]);
                        //根据订单号查询出该条出库单记录
                        OutboundorderModel outboundorderModel =outboundorderServiceImpl.selectByOid(exOid[j]);
                        //再次将出库单信息发送给WMS
                        orderServiceImpl.sendOutboundOrder(orderModel,outboundorderModel,userName);
                        //再删除异常页面的异常订单
                        exceptionServiceImpl.deleteByOid(exOid[j]);
                    }
                }
            }
        }else{
            System.out.println("异常类型不完全相同");
            return "{\"msg\":\"1\"}";
        }
        return "OMSPage";
    }

    //子页面显示
    @RequestMapping(value="listExceptionSon")
    @ResponseBody
    public String listExceptionSon(HttpServletRequest request )
    {
        String str=exceptionServiceImpl.listExceptionSon(request);
        return str;
    }

    //异常订单详细页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        String oid3=request.getParameter("oid3");
        //查询异常订单列表
        ExceptionModel exceptionList = exceptionServiceImpl.selectByOid(oid3);
        /* 获取商品编码  查询关系表 */
        List<RelationogModel> rogList= relationogServiceImpl.selectALLByOid(oid3);
        //获取商品实体 查询商品表
        List<Object> godsList=new ArrayList<Object>();
        for(int i=0;i<rogList.size();i++){
            GoodsPojo gp=new GoodsPojo();
            //获取商品编号
            String sno= rogList.get(i).getGoodsno();
            //获取商品数量
            int snum= rogList.get(i).getGoodnum() ;
            GoodsModel gm=goodsServiceImpl.selectByGoodsNo(sno);
            gp.setGoodsnum(snum);
            gp.setGoodsname(gm.getGoodsname());
            gp.setGoodsno(gm.getGoodsno());
            gp.setGoodsprice(gm.getGoodsprice());
            godsList.add(gp);
        }
        model.addAttribute("gods",godsList);
        model.addAttribute("exceptionList",exceptionList);
        model.addAttribute("rog",rogList);
        model.addAttribute("oid",oid3);

        return "exceptionDetails";
    }


}
