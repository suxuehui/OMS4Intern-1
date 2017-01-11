package com.arvato.oms.controller;

import com.arvato.oms.ExceptionModel.NewRunException;
import com.arvato.oms.model.*;
import com.arvato.oms.service.*;
import com.arvato.oms.utils.HTTPClientDemo;
import com.arvato.oms.utils.ReadProperties;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */

@Controller
@RequestMapping("/refoundOrder/")
public class RefoundOrderController {
    private Logger log = Logger.getLogger(ExceptionController.class);
    ReadProperties rpr = new ReadProperties();
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
    @Resource
    private RelationogService relationogServiceImpl;

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
        String drawbackId=request.getParameter("drawbackId");
        //查询退款单列表
        RefoundOrderModel refoundOrderList = refoundOrderServiceImpl.selectByDrawbackId(drawbackId);
        String returnedId = refoundOrderList.getReturnedid();
        String oid = drawbackId.substring(2,17);
        List<RelationogModel> rogList2= relationogServiceImpl.selectALLByOid(oid);
        if(returnedId==null||"".equals(returnedId)){
            //获取商品实体 查询商品表
            List<Object> godsList2=new ArrayList<Object>();
            for(int i=0;i<rogList2.size();i++){
                GoodsPojo gp=new GoodsPojo();
                //获取商品编号
                String sno= rogList2.get(i).getGoodsno();
                //获取商品数量
                int snum= rogList2.get(i).getGoodnum() ;
                //获取单个商品折扣后的价格
                BigDecimal divideorderfee = rogList2.get(i).getDivideorderfee();
                GoodsModel gm= goodsModelServiceImpl.selectByGoodsNo(sno);
                gp.setGoodsnum(snum);
                gp.setGoodsname(gm.getGoodsname());
                gp.setGoodsno(gm.getGoodsno());
                gp.setGoodsprice(gm.getGoodsprice());
                gp.setDivideorderfee(divideorderfee);
                godsList2.add(gp);
            }
            model.addAttribute("gods",godsList2);
            model.addAttribute("refoundOrderList",refoundOrderList);
            model.addAttribute("rog",rogList2);
            model.addAttribute("oid",oid);
            return "refoundOrderDetails";
        }
        /* 获取商品编码  查询关系表*/
        List<RelationrgModel> rogList = relationrgServiceImpl.selectALLByReturnedId(returnedId);
        //获取商品实体 查询商品表
        List<Object> godsList=new ArrayList<Object>();
        for(int i=0;i<rogList.size();i++){
            GoodsPojo gp=new GoodsPojo();
            //获取商品编号
            String sno= rogList.get(i).getGoodsno();
            //获取商品数量
            int snum= rogList.get(i).getGoodnum() ;
            //获取单个商品折扣后的价格
            BigDecimal divideorderfee = rogList2.get(i).getDivideorderfee();
            GoodsModel gm= goodsModelServiceImpl.selectByGoodsNo(sno);
            gp.setGoodsnum(snum);
            gp.setGoodsname(gm.getGoodsname());
            gp.setGoodsno(gm.getGoodsno());
            gp.setGoodsprice(gm.getGoodsprice());
            gp.setDivideorderfee(divideorderfee);
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
    public String refoundInterface(HttpServletRequest request, HttpSession session) {
        String drawbackId3=request.getParameter("drawbackId3");
        RefoundOrderModel refoundOrderModel = refoundOrderServiceImpl.selectByDrawbackId(drawbackId3);
        //判断该退款单是否已经退过款了
        String drawbackstatus=refoundOrderModel.getDrawbackstatus();
        if("退款成功".equals(drawbackstatus)){
            return "{\"msg\":\"111\"}";
        }
        //退款单号
        String drawbackId = refoundOrderModel.getDrawbackid();
        //退款金额
        String drawbackMoney = refoundOrderModel.getDrawbackmoney().toString();
        log.info("-----------------------------------drawbackMoney:"+drawbackMoney);
        //订单号
        String oid2 = drawbackId.substring(2,17);
        OrderModel orderModel = orderServiceImpl.selectByOid(oid2);
        //渠道订单号
        String channelOid=orderModel.getChanneloid();
        //退款账号
        String buyerAlipayNo=orderModel.getBuyeralipayno();
        JSONObject object = new JSONObject();
        object.put("orderNumbers",oid2);//订单号
        object.put("refundNumbers",drawbackId);//退款单号
        object.put("originNumbers",channelOid);//渠道订单号
        object.put("refundment",drawbackMoney);//退款金额
        object.put("refundAccount",buyerAlipayNo);//退款账号
        log.info("-----------------------------------object:"+object.toString());
        String sendRefoundOrderUrl=null;
        try {
            sendRefoundOrderUrl = rpr.readProperties("url.properties","sendRefoundOrderUrl");
        }catch (IOException e)
        {
            log.info(e);
            throw new NewRunException("找不到url.properties文件");
        }
        HTTPClientDemo httpClientDemo = new HTTPClientDemo(sendRefoundOrderUrl);
        //得到返回信息
        String returnInformation = httpClientDemo.postMethod(object.toString());
        String code = null;
        try {
            JSONObject returnObject = JSONObject.fromObject(returnInformation);
            code = returnObject.getString("code");
        }catch (Exception e){
            log.info(e);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dataNow = df.format(new Date());//修改时间
        String userName = (String)session.getAttribute("uname");//修改人
        //退款成功
        if("666".equals(code))
        {
            //将退款状态改为已退款
            String drawbackStatus = "退款成功";
            refoundOrderServiceImpl.updataRefoundDrawbackId(drawbackStatus,dataNow,userName,drawbackId);
            return "{\"msg\":\"666\"}";
        }
        //退款失败
        else if("777".equals(code))
        {
            String drawbackStatus = "退款失败";
            refoundOrderServiceImpl.updataRefoundDrawbackId(drawbackStatus,dataNow,userName,drawbackId);
            return "{\"msg\":\"777\"}";
        }
        //信息发送失败
        else
        {
            log.info("code:"+code);
            return "{\"msg\":\"123\"}";
        }
    }
}
