package com.arvato.oms.controller;

import com.arvato.oms.model.*;
import com.arvato.oms.service.*;
import com.arvato.oms.utils.HTTPClientDemo;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */

@Controller
@RequestMapping("/refoundOrder/")
public class RefoundOrderController {
    private Logger log = Logger.getLogger(ExceptionController.class);
    @Resource
    private RefoundOrderService refoundOrderServiceImpl;
    @Resource
    private RelationrgService relationrgServiceImpl;
    @Resource
    private GoodsModelService goodsModelServiceImpl;
    @Resource
    private ReturnedModelService returnedModelServiceImpl;
    @Resource
    private OrderService orderServiceImpl;

    //进入页面
    @RequestMapping(value="indexRefoundOrderList")
    public String indexRefoundOrderList(HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        return "OMSPage";
    }

    //分页显示出所有异常订单
    @RequestMapping(value = "showRefoundOrderList")
    @ResponseBody
    public String showRefoundOrderList(HttpServletRequest request){
        return  refoundOrderServiceImpl.showRefoundOrderList(request);
    }

    //子页面显示
    @RequestMapping(value="listRefoundOrderSon")
    @ResponseBody
    public String listRefoundOrderSon(HttpServletRequest request )
    {
        return refoundOrderServiceImpl.listRefoundOrderSon(request);
    }

    //退款单详细页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        String returnedId=request.getParameter("returnedId");
        //查询退款单列表
        RefoundOrderModel refoundOrderList = refoundOrderServiceImpl.selectByReturnedId(returnedId);
        /* 获取商品编码  查询关系表 */
        List<RelationrgModel> rogList = relationrgServiceImpl.selectALLByReturnedId(returnedId);
        //获取商品实体 查询商品表
        List<Object> godsList=new ArrayList<Object>();
        for(int i=0;i<rogList.size();i++){
            GoodsPojo gp=new GoodsPojo();
            //获取商品编号
            String sno= rogList.get(i).getGoodsno();
            //获取商品数量
            int snum= rogList.get(i).getGoodnum() ;
            GoodsModel gm= goodsModelServiceImpl.selectByGoodsNo(sno);
            gp.setGoodsnum(snum);
            gp.setGoodsname(gm.getGoodsname());
            gp.setGoodsno(gm.getGoodsno());
            gp.setGoodsprice(gm.getGoodsprice());
            godsList.add(gp);
        }
        model.addAttribute("gods",godsList);
        model.addAttribute("refoundOrderList",refoundOrderList);
        model.addAttribute("rog",rogList);
        model.addAttribute("oid",returnedId);

        return "refoundOrderDetails";
    }

    //调退款接口
    @RequestMapping(value="drawback")
    @ResponseBody
    public String refoundInterface(HttpServletRequest request, HttpServletResponse response) {
        String returnedId3=request.getParameter("returnedId3");
        RefoundOrderModel refoundOrderModel = refoundOrderServiceImpl.selectByReturnedId(returnedId3);
        //退款单号
        String drawbackId = refoundOrderModel.getDrawbackid();
        //退款金额
        BigDecimal drawbackMoney = refoundOrderModel.getDrawbackmoney();
        ReturnedModel returnedModel = returnedModelServiceImpl.selectByReturnedId(returnedId3);
        //订单号
        String returnedoId = returnedModel.getOid();
        OrderModel orderModel = orderServiceImpl.selectByOid(returnedoId);
        //渠道订单号
        String channelOid=orderModel.getChanneloid();
        //退款账号
        String buyerAlipayNo=orderModel.getBuyeralipayno();
        JSONObject object = new JSONObject();
        object.put("orderNumbers",returnedoId);//订单号
        object.put("refundNumbers",drawbackId);//退款单号
        object.put("originNumbers",channelOid);//渠道订单号
        object.put("refundment",drawbackMoney);//退款金额
        object.put("refundAccount",buyerAlipayNo);//退款账号
        HTTPClientDemo httpClientDemo = new HTTPClientDemo("http://114.215.252.146:8080/DGFORQ3/oms/api/v1/refund");
        //得到返回信息
        String returnInformation = httpClientDemo.postMethod(object.toString());
        String code = null;
        try {
            JSONObject returnObject = JSONObject.fromObject(returnInformation);
            code = returnObject.getString("code");
        }catch (Exception e){
            log.info(e);
        }
        //退款成功
        if("666".equals(code))
        {
            //将退款状态改为已退款
            String drawbackStatus = "已退款";
            refoundOrderServiceImpl.updataRefoundDrawbackId(drawbackStatus,drawbackId);
            return "{\"msg\":\"666\"}";
        }
        //随机数退款失败
        else if("777".equals(code))
        {
            return "{\"msg\":\"777\"}";
        }
        //退款失败
        else
        {
            return "{\"msg\":\"123\"}";
        }
    }
}
