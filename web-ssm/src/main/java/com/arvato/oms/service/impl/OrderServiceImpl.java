package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.arvato.oms.ExceptionModel.NewRunException;
import com.arvato.oms.ImportTradeModel.Order;
import com.arvato.oms.dao.*;
import com.arvato.oms.model.*;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.utils.HTTPClientDemo;
import com.arvato.oms.utils.Page;
import com.arvato.oms.utils.ReadProperties;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Service
public class OrderServiceImpl implements OrderService
{
    @Resource
    OrderModelMapper orderModelMapper;
    @Resource
    RelationogModelMapper relationogModelMapper;
    @Resource
    GoodsModelMapper goodsModelMapper;
    @Resource
    ExceptionModelMapper exceptionModelMapper;
    @Resource
    OutboundorderModelMapper outboundorderModelMapper;
    @Resource
    ReturnedModelMapper returnedModelMapper;
    @Resource
    RelationrgModelMapper relationrgModelMapper;
    @Resource
    RefoundOrderModelMapper refoundOrderModelMapper;
    ReadProperties rp = new ReadProperties();
    private Logger log = Logger.getLogger(OrderServiceImpl.class);
    //根据订单号分页查询商品信息
    public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    {
        JSONObject jObj=new JSONObject();
        int num=relationogModelMapper.selectCount(oId);
        Page page=new Page(num,pageNo,pageSize);
        int count=page.getStartPos();
        int pageTotal=page.getTotalPageCount();
        List<GoodsPojo> goodsPojos=goodsModelMapper.selectByOid(count,pageSize,oId);
        jObj.put("pageTotal",pageTotal);
        jObj.put("goodsPojos",goodsPojos);
        jObj.put("pageNo",pageNo);
        return jObj;
    }
    //根据订单号精确查询订单
    public OrderModel selectByOid(String oid)
    {
        OrderModel orderModel=orderModelMapper.selectByOid(oid);
        return orderModel;
    }
    //根据订单号查询所有商品信息
    public List<GoodsPojo> selectGoodsByOid(String oid)
    {
        return goodsModelMapper.selectAllByOid(oid);
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
        List<OrderModel> orderModels=null;
        int pageTotal=0;
        if(queryMode==1)//按订单号查询
        {
            int num=orderModelMapper.CountByOid(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByOids(count,pageSize,data);
        }
        else if (queryMode==2)//按渠道订单号查询
        {
            int num=orderModelMapper.CountByChanneloid(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByChanneloids(count,pageSize,data);
        }
        else if (queryMode==3)//按订单状态查询
        {
            int num=orderModelMapper.CountByOrderStatus(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByOrderStatuss(count,pageSize,data);
        }
        else if (queryMode==4)//按支付方式查询
        {
            int num=orderModelMapper.CountByPayStyle(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByPayStyles(count,pageSize,data);
        }
        else if (queryMode==5)//按物流公司查询
        {
            int num=orderModelMapper.CountByLogisticsCompany(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByLogisticsCompanys(count,pageSize,data);
        }
        else if (queryMode==6)//按省查询
        {
            int num=orderModelMapper.CountByReceiverProvince(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByReceiverProvinces(count,pageSize,data);
        }
        else if (queryMode==7)//按市查询
        {
            int num=orderModelMapper.CountByReceiverCity(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByReceiverCitys(count,pageSize,data);
        }
        else if (queryMode==8)//按区查询
        {
            int num=orderModelMapper.CountByReceiverArea(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByReceiverAreas(count,pageSize,data);
        }
        else if (queryMode==9)//按收货人手机号查询
        {
            int num=orderModelMapper.CountByReceiverMobel(data);
            Page page=new Page(num,pageNo,pageSize);
            int count=page.getStartPos();
            pageTotal=page.getTotalPageCount();
            orderModels=orderModelMapper.selectByReceiverMobels(count,pageSize,data);
        }
        JSONObject jObj=new JSONObject();
        jObj.put("pageTotal",pageTotal);
        jObj.put("pageNo",pageNo);
        jObj.put("orderModels",orderModels);
        return jObj;
    }
    //更新订单
    public int updateByOidSelective(OrderModel record)
    {
        return orderModelMapper.updateByOidSelective(record);
    }
    //预检订单
    @Transactional(rollbackFor = RuntimeException.class)
    public int previewOrder(String oId, String exceptionType,String name) throws RuntimeException
    {
        OrderModel orderModel=selectByOid(oId);
        List<String> exceptionList=Arrays.asList("商品异常","库存异常","金额异常","备注异常",null);
        if(orderModel==null)
        {
            return 3;//订单号不存在
        }
        if(!orderModel.getOrderstatus().equals("待预检"))
        {
            return 2;//订单状态不符
        }
        if(!exceptionList.contains(exceptionType))
        {
            return 4;//异常类型有错
        }
        List<RelationogModel> relationogModelList=relationogModelMapper.selectAllByOid(oId);
        ExceptionModel exceptionModel;
        if(exceptionType==null||exceptionType.equals("商品异常"))
        {
            for(RelationogModel re:relationogModelList)
            {
                GoodsModel goodsModel=goodsModelMapper.selectByPrimaryKey(re.getGoodsno());
                if(goodsModel==null||goodsModel.getGoodsstate().equals("已下架"))
                {
                    exceptionModel=createException(orderModel);
                    exceptionModel.setExceptiontype("商品异常");
                    exceptionModel.setExpceptioncause("该订单包含不存在或已下架的商品");
                    exceptionModel.setOrderstatus("订单异常");
                    exceptionModel.setOrderfrom("预检");
                    orderModel.setOrderstatus("订单异常");
                    orderModel.setModifytime(new Date());
                    orderModel.setModifyman(name);
                    orderModelMapper.updateByOidSelective(orderModel);
                    if(checkException(oId))
                    {
                        exceptionModelMapper.insertSelective(exceptionModel);
                    }
                    return 5;
                }
            }
        }
        if(exceptionType==null||exceptionType.equals("库存异常"))
        {
            for(RelationogModel re:relationogModelList)
            {
                GoodsModel goodsModel=goodsModelMapper.selectByPrimaryKey(re.getGoodsno());
                int goodsRnum;
                if(relationogModelMapper.selectGoodsRnum(oId)==null)
                {
                    goodsRnum=0;
                }
                else
                {
                    goodsRnum =relationogModelMapper.selectGoodsRnum(oId);
                }
                int goodsVnum=goodsModel.getGoodstolnum()-goodsRnum;
                if(re.getGoodnum()>goodsVnum)
                {
                    exceptionModel=createException(orderModel);
                    exceptionModel.setExceptiontype("库存异常");
                    exceptionModel.setExpceptioncause("库存不足");
                    exceptionModel.setOrderstatus("订单异常");
                    exceptionModel.setOrderfrom("预检");
                    orderModel.setOrderstatus("订单异常");
                    orderModel.setModifytime(new Date());
                    orderModel.setModifyman(name);
                    orderModelMapper.updateByOidSelective(orderModel);
                    if(checkException(oId))
                    {
                        exceptionModelMapper.insertSelective(exceptionModel);
                    }
                    return 5;
                }
            }
        }
        if((exceptionType==null||exceptionType.equals("金额异常"))&&orderModel.getOrdertolprice().doubleValue()>=10000)
        {
            exceptionModel=createException(orderModel);
            exceptionModel.setExceptiontype("金额异常");
            exceptionModel.setExpceptioncause("金额大于10000");
            exceptionModel.setOrderstatus("订单异常");
            exceptionModel.setOrderfrom("预检");
            orderModel.setOrderstatus("订单异常");
            orderModel.setModifytime(new Date());
            orderModel.setModifyman(name);
            orderModelMapper.updateByOidSelective(orderModel);
            if(checkException(oId))
            {
                exceptionModelMapper.insertSelective(exceptionModel);
            }
            return 5;
        }
        if((exceptionType==null||exceptionType.equals("备注异常"))&&orderModel.getRemark()!=null&&!orderModel.getRemark().equals(""))
        {
            exceptionModel=createException(orderModel);
            exceptionModel.setExceptiontype("备注异常");
            exceptionModel.setExpceptioncause("订单有备注内容");
            exceptionModel.setOrderstatus("订单异常");
            exceptionModel.setOrderfrom("预检");
            orderModel.setOrderstatus("订单异常");
            orderModel.setModifytime(new Date());
            orderModel.setModifyman(name);
            orderModelMapper.updateByOidSelective(orderModel);
            if(checkException(oId))
            {
                exceptionModelMapper.insertSelective(exceptionModel);
            }
            return 5;
        }
        //book库存
        for(RelationogModel re:relationogModelList)
        {
            re.setStatus((byte)1);
            relationogModelMapper.updateByPrimaryKeySelective(re);
        }
        orderModel.setOrderstatus("待路由");
        orderModel.setModifytime(new Date());
        orderModel.setModifyman(name);
        orderModelMapper.updateByOidSelective(orderModel);
        return 1;
    }
    //判断异常列表中该订单号是否存在
    public boolean checkException(String oId)
    {
        ExceptionModel exceptionModel=exceptionModelMapper.selectByExceptionOid(oId);
        if(exceptionModel==null)
        {
            return true;
        }
        return false;
    }
    public ExceptionModel createException(OrderModel orderModel)
    {
        ExceptionModel exceptionModel=new ExceptionModel();
        exceptionModel.setOid(orderModel.getOid());
        exceptionModel.setChanneloid(orderModel.getChanneloid());
        exceptionModel.setExceptionstatus("待处理");
        exceptionModel.setCreatetime(new Date());
        return exceptionModel;
    }
    //取消订单
    @Transactional(rollbackFor = RuntimeException.class)
    public int cancleOrder(String oId,String uname) throws RuntimeException
    {
        if(oId==null)
        {
            return 2;//订单不存在
        }
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(orderModel==null)
        {
            return 3;//订单不存在
        }
        List<RelationogModel> relationogModelList=relationogModelMapper.selectAllByOid(oId);
        List<String> statusList= Arrays.asList("待预检","待路由","待出库");
        if(statusList.contains(orderModel.getOrderstatus()))
        {
            OutboundorderModel outboundorderModel=outboundorderModelMapper.selectByOid(oId);
            if(outboundorderModel!=null)
            {
                outboundorderModel.setOrderstatus("已取消");
                outboundorderModel.setOutboundstate("已取消");
                outboundorderModel.setModifytime(new Date());
                outboundorderModel.setModifyman(uname);
                outboundorderModelMapper.updateByOidSelective(outboundorderModel);
            }
            orderModel.setOrderstatus("已取消");
            orderModel.setModifytime(new Date());
            orderModel.setModifyman(uname);
            orderModelMapper.updateByOidSelective(orderModel);
            //取消该订单的锁定库存
            for(RelationogModel re:relationogModelList)
            {
                re.setStatus((byte)0);
                relationogModelMapper.updateByPrimaryKeySelective(re);
            }
        }
        if(orderModel.getOrderstatus().equals("已出库"))
        {
            OutboundorderModel outboundorderModel=outboundorderModelMapper.selectByOid(oId);
            Properties p=null;
            try {
                p = rp.readProperties("url.properties");
            }catch (IOException e)
            {
                log.info(e);
                throw new NewRunException("找不到url.properties文件");
            }
            String cancelOrderUrl=(String)p.get("cancleOrderUrl");
            HTTPClientDemo httpClientDemo=new HTTPClientDemo(cancelOrderUrl);
            String str=httpClientDemo.getMethod("outboundorderid",outboundorderModel.getOutboundid());
            String code="";
            try
            {
                JSONObject jsonObject=JSON.parseObject(str);
                code=jsonObject.getString("code");
                if(code.equals("100"))
                {
                    outboundorderModel.setOrderstatus("已取消");
                    outboundorderModel.setOutboundstate("已取消");
                    outboundorderModel.setModifytime(new Date());
                    outboundorderModel.setModifyman(uname);
                    outboundorderModelMapper.updateByOidSelective(outboundorderModel);
                    orderModel.setOrderstatus("已取消");
                    orderModel.setModifytime(new Date());
                    orderModel.setModifyman(uname);
                    orderModelMapper.updateByOidSelective(orderModel);
                    //取消该订单的锁定库存
                    for(RelationogModel re:relationogModelList)
                    {
                        re.setStatus((byte)0);
                        relationogModelMapper.updateByPrimaryKeySelective(re);
                    }
                }
            }
            catch (Exception e)
            {
                log.info(e);
                return 4;//接口连接异常
            }
            if(code.equals("101"))
            {
                return 5;//出库单号为空
            }
            if(code.equals("102"))
            {
                return 6;//没有该出库单
            }
            if(code.equals("103"))
            {
                return 7;//订单已取消
            }
            if(code.equals("104"))
            {
                return 8;//订单已发货，无法取消
            }
        }
        try {
            return createReturned(oId);
        }catch(Exception e)
        {
            log.info(e);
            throw new NewRunException("数据库更新异常");
        }
    }
    //根据订单号生成退款单
    public int createReturned(String oId) throws RuntimeException
    {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        RefoundOrderModel refoundOrderModel=new RefoundOrderModel();
        String refoundId="RF"+oId+(int)(Math.random()*90000+10000);
        refoundOrderModel.setDrawbackid(refoundId);
        refoundOrderModel.setDrawbackmoney(orderModel.getOrdertolprice());
        refoundOrderModel.setDrawbackstatus("未退款");
        refoundOrderModel.setCreatetime(new Date());
        RefoundOrderModel refoundOrderModel1=refoundOrderModelMapper.selectByRefoundId(refoundId);
        if(refoundOrderModel1!=null)
        {
            return 6;//退款单已存在
        }
        return refoundOrderModelMapper.insertSelective(refoundOrderModel);
    }
    //路由
    public int routeOrder(String oId,String uname) {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(orderModel==null)
        {
            return 3;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("待路由"))
        {
            return 2;//订单状态不符
        }
        orderModel.setGoodswarehouse("南京仓");
        orderModel.setOrderstatus("待出库");
        orderModel.setModifytime(new Date());
        orderModel.setModifyman(uname);
        orderModelMapper.updateByOidSelective(orderModel);
        return 1;
    }
    //生成出库单
    @Transactional(rollbackFor = RuntimeException.class)
    public int outboundOrder(String oId,String uname) throws RuntimeException{
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(orderModel==null)
        {
            return 2;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("待出库"))
        {
            return 3;//订单状态不符
        }
        OutboundorderModel outboundorderModel1=outboundorderModelMapper.selectByOid(oId);
        if(outboundorderModel1!=null)
        {
            return 0;
        }
        OutboundorderModel outboundorderModel=new OutboundorderModel();
        outboundorderModel.setOid(orderModel.getOid());
        outboundorderModel.setChanneloid(orderModel.getChanneloid());
        outboundorderModel.setOutboundid("SO"+orderModel.getOid()+(int)(Math.random()*90000+10000));
        outboundorderModel.setSynchrostate(false);
        outboundorderModel.setReceivername(orderModel.getReceivername());
        outboundorderModel.setReceiveraddress(orderModel.getReceiverprovince()+orderModel.getReceivercity()+orderModel.getReceiverarea()+orderModel.getDetailaddress());
        outboundorderModel.setCreatedtime(new Date());
        outboundorderModelMapper.insert(outboundorderModel);
        try
        {
            return sendOutboundOrder(orderModel,outboundorderModel,uname);
        }catch(RuntimeException e)
        {
            log.info(e);
            throw new NewRunException("更新数据库异常");
        }
    }
    //调用WMS接口发送出库单
    public int sendOutboundOrder (OrderModel orderModel,OutboundorderModel outboundorderModel,String uname) throws RuntimeException
    {
        List<GoodsPojo> goodsPojoList=goodsModelMapper.selectAllByOid(orderModel.getOid());
        List<WMSOutBoundGoods> outBoundGoodsList=new ArrayList<WMSOutBoundGoods>();
        for(int i=0;i<goodsPojoList.size();i++)
        {
            WMSOutBoundGoods wmsGoods=new WMSOutBoundGoods();
            GoodsPojo goods=goodsPojoList.get(i);
            wmsGoods.setSku(goods.getGoodsno());
            wmsGoods.setName(goods.getGoodsname());
            wmsGoods.setNum(goods.getGoodNum());
            wmsGoods.setPrice(goods.getGoodsprice());
            wmsGoods.setTotalprice(BigDecimal.valueOf(goods.getGoodNum()*(goods.getGoodsprice().doubleValue())));
            wmsGoods.setOutboundnum(goods.getGoodNum());
            outBoundGoodsList.add(wmsGoods);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orderid",orderModel.getOid());
        jsonObject.put("outboundorderid",outboundorderModel.getOutboundid());
        jsonObject.put("channelorderid",outboundorderModel.getChanneloid());
        jsonObject.put("message",orderModel.getRemark());
        JSONObject deliveryOrder=new JSONObject();
        deliveryOrder.put("receiver",outboundorderModel.getReceivername());
        deliveryOrder.put("receivertel",orderModel.getReceivermobel());
        deliveryOrder.put("receiveraddress",outboundorderModel.getReceiveraddress());
        jsonObject.put("outboundordergoods",outBoundGoodsList);
        jsonObject.put("deliveryOrder",deliveryOrder);
        Properties p;
        try {
            p = rp.readProperties("url.properties");
        }catch (IOException e)
        {
            log.info(e);
            throw new NewRunException("找不到url.properties文件");
        }
        String sendOutboundOrderUrl=(String)p.get("sendOutboundOrderUrl");
        HTTPClientDemo httpClientDemo=new HTTPClientDemo(sendOutboundOrderUrl);
        String response=httpClientDemo.postMethod(jsonObject.toString());
        String code="";
        try {
            code = JSON.parseObject(response).getString("code");
        }
        catch(Exception e)
        {
            log.info(e);
            code="接口连接异常";
        }
        if(code.equals("100"))
        {
            orderModel.setOrderstatus("已出库");
            orderModel.setModifyman(uname);
            orderModel.setModifytime(new Date());
            orderModelMapper.updateByOidSelective(orderModel);
            outboundorderModel.setOrderstatus("已出库");
            outboundorderModel.setOutboundstate("处理中");
            outboundorderModel.setSynchrostate(true);
            outboundorderModel.setModifyman(uname);
            outboundorderModel.setModifytime(new Date());
            outboundorderModelMapper.updateByOidSelective(outboundorderModel);
            //删除book库存
            List<RelationogModel> relationogModelList=relationogModelMapper.selectAllByOid(orderModel.getOid());
            for(RelationogModel re:relationogModelList)
            {
                re.setStatus((byte)0);
                relationogModelMapper.updateByPrimaryKeySelective(re);
            }
            return 1;
        }
        if(code.equals("101"))
        {
            code="重复的出库单";
        }
        if(code.equals("102"))
        {
            code="订单号为空";
        }
        if(code.equals("103"))
        {
            code="订单号格式错误";
        }
        if(code.equals("104"))
        {
            code="出库单号为空";
        }
        if(code.equals("105"))
        {
            code="出库单号格式错误";
        }
        if(code.equals("106"))
        {
            code="渠道订单号为空";
        }
        if(code.equals("107"))
        {
            code="收货人信息为空";
        }
        if(code.equals("108"))
        {
            code="收货人电话为空";
        }
        if(code.equals("109"))
        {
            code="收货人电话格式不正确";
        }
        if(code.equals("110"))
        {
            code="收货人为空";
        }
        if(code.equals("111"))
        {
            code="收货地址为空";
        }
        if(code.equals("112"))
        {
            code="商品信息为空";
        }
        if(code.equals("113"))
        {
            code="商品信息不正确";
        }
        ExceptionModel exceptionModel=createException(orderModel);
        exceptionModel.setExceptiontype("出库异常");
        exceptionModel.setExpceptioncause(code);
        exceptionModel.setOrderstatus("出库异常");
        exceptionModel.setOrderfrom("出库");
        exceptionModelMapper.insertSelective(exceptionModel);
        outboundorderModel.setOrderstatus("出库异常");
        outboundorderModel.setOutboundstate("出库异常");
        outboundorderModel.setSynchrostate(true);
        outboundorderModel.setModifytime(new Date());
        outboundorderModel.setModifyman(uname);
        outboundorderModelMapper.updateByOidSelective(outboundorderModel);
        orderModel.setOrderstatus("出库异常");
        orderModel.setModifytime(new Date());
        orderModel.setModifyman(uname);
        orderModelMapper.updateByOidSelective(orderModel);
        return 5;
    }
    //导入订单
    @Transactional(rollbackFor = Exception.class)
    public int importOrder(String str) throws RuntimeException{
        JSONObject jsonObject=null;
        try {
            jsonObject = JSON.parseObject(str);
        }
        catch(Exception e)
        {
            log.info(e);
            return 4;//excel内容错误
        }
        JSONObject tradeJson=jsonObject.getJSONObject("trade_fullinfo_get_response").getJSONObject("trade");
        String orders= tradeJson.getJSONObject("orders").getString("order");
        ArrayList<Order> orderList=JSON.parseObject(orders,new TypeReference<ArrayList<Order>>(){});
        OrderModel orderModel=new OrderModel();
        RelationogModel relationogModel=new RelationogModel();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String oId="OO"+simpleDateFormat.format(new Date())+(int)(Math.random()*90000+10000);
        orderModel.setOid(oId);
        //判断该渠道订单是否已经存在
        OrderModel orderModel1=orderModelMapper.selectByChannelOid(tradeJson.getString("tid"));
        if(orderModel1!=null)
        {
            return 6;//订单存在
        }
        orderModel.setChanneloid(tradeJson.getString("tid"));
        orderModel.setOrderstatus("待预检");
        orderModel.setOrderform(tradeJson.getString("trade_from"));
        orderModel.setBuyerid(tradeJson.getString("buyer_nick"));
        orderModel.setOrdertime(tradeJson.getDate("created"));
        orderModel.setBasestatus("活动");
        orderModel.setPaystatus("已支付");
        orderModel.setPaystyle("支付宝");
        orderModel.setPaytime(tradeJson.getDate("pay_time"));
        orderModel.setGoodstolprice(tradeJson.getBigDecimal("total_fee"));
        orderModel.setOrdertolprice(tradeJson.getBigDecimal("payment"));
        orderModel.setRemark(tradeJson.getString("buyer_message"));
        orderModel.setReceivername(tradeJson.getString("receiver_name"));
        orderModel.setReceivermobel(tradeJson.getString("receiver_mobile"));
        orderModel.setReceivertelnum("");
        orderModel.setReceiverprovince(tradeJson.getString("receiver_state"));
        orderModel.setReceivercity(tradeJson.getString("receiver_city"));
        orderModel.setReceiverarea(tradeJson.getString("receiver_district"));
        orderModel.setDetailaddress(tradeJson.getString("receiver_address"));
        orderModel.setZipcode(tradeJson.getString("receiver_zip"));
        orderModel.setBuyeralipayno(tradeJson.getString("buyer_alipay_no"));
        double discountPrice=Double.parseDouble(tradeJson.getString("total_fee"))-Double.parseDouble(tradeJson.getString("payment"));
        orderModel.setDiscountprice(discountPrice);
        for(Order order:orderList)
        {
            relationogModel.setOid(oId);
            relationogModel.setGoodsno(order.getNum_iid());
            relationogModel.setGoodnum(order.getNum());
            relationogModel.setStatus((byte)0);
            BigDecimal divideorderfee;
            BigDecimal totalfee=new BigDecimal(order.getTotal_fee());
            if(order.getDivide_order_fee()!=null)
            {
                divideorderfee=new BigDecimal(order.getDivide_order_fee());
            }
            else
            {
                divideorderfee=BigDecimal.valueOf((totalfee.doubleValue())/(order.getNum()));
            }
            relationogModel.setDivideorderfee(divideorderfee);
            relationogModel.setTotalfee(totalfee);
            relationogModelMapper.insertSelective(relationogModel);
        }
        orderModelMapper.insertSelective(orderModel);
        return 1;
    }

    //退换货
    @Transactional(rollbackFor = RuntimeException.class)
    public int returnGoods(String jsonStr) throws RuntimeException
    {
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        ArrayList<GoodsPojo> goodsList=JSON.parseObject(jsonObject.getString("goods"),new TypeReference<ArrayList<GoodsPojo>>(){});
        OrderModel orderModel=orderModelMapper.selectByOid(jsonObject.getString("oid"));
        RelationrgModel[] relationRgModels=new RelationrgModel[goodsList.size()];
        ReturnedModel returnedModel=returnedModelMapper.selectByOid(orderModel.getOid());
        if(returnedModel!=null&&!returnedModel.getReturnedstatus().equals("取消退货")&&!returnedModel.getReturnedstatus().equals("取消换货"))
        {
            return 0;//订单退货中
        }
        returnedModel=new ReturnedModel();
        returnedModel.setOid(jsonObject.getString("oid"));
        if(orderModel==null)
        {
            return 0;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("已完成"))
        {
            return 2;//订单未完成
        }
        if(goodsList.size()==0)
        {
            return 3;//没有选择商品
        }
        returnedModel.setChanneloid(orderModel.getChanneloid());
        String returnedid="RT"+jsonObject.getString("oid")+(int)(Math.random()*90000+10000);
        returnedModel.setReturnedid(returnedid);
        returnedModel.setReturnedorchange(jsonObject.getString("returnedOrChange"));
        returnedModel.setBuyeralipayno(orderModel.getBuyeralipayno());
        double returnedMoney=0.00;
        for(int i=0;i<goodsList.size();i++)
        {
            returnedMoney+=goodsList.get(i).getGoodNum()*(goodsList.get(i).getDivideorderfee()).doubleValue();
            relationRgModels[i]=new RelationrgModel();
            relationRgModels[i].setReturnedid(returnedid);
            relationRgModels[i].setGoodsno(goodsList.get(i).getGoodsno());
            relationRgModels[i].setGoodnum(goodsList.get(i).getGoodNum());
        }
        returnedModel.setReturnedmoney(BigDecimal.valueOf(returnedMoney));
        returnedModel.setReturnedstatus("待审核");
        Date date=new Date();
        returnedModel.setCreatetime(date);
        returnedModelMapper.insertSelective(returnedModel);
        for(RelationrgModel re:relationRgModels)
        {
            relationrgModelMapper.insertSelective(re);
        }
        return 1;
    }
    //检查订单是否可以退换货
    public int checkreturn(String oid)
    {
        ReturnedModel returnedModel=returnedModelMapper.selectByOid(oid);
        if(returnedModel!=null&&!returnedModel.getReturnedstatus().equals("取消退货")&&!returnedModel.getReturnedstatus().equals("取消换货"))
        {
            return 1;//不可以退换货
        }
        return 0;//可以退换货操作
    }

    //更新订单列表的订单状态
    public void updateOrder(String orderStatus,Date time,String userName,String expressCompany,String expressId,String oid) {
        orderModelMapper.updateOrder(orderStatus,time,userName,expressCompany,expressId,oid);
    }

    public void updateOrder2(String orderStatus,Date time,String userName,String oid) {
        orderModelMapper.updateOrder2(orderStatus,time,userName,oid);
    }
    //解析excel文件
    public int analysisExcel(String filePath)
    {
        XSSFWorkbook hssfWorkbook=null;
        try {
            InputStream is = new FileInputStream(filePath);
            hssfWorkbook = new XSSFWorkbook(is);
            is.close();
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                XSSFCell tradeStr = hssfRow.getCell(0);
                int j=importOrder(tradeStr.toString());
                if(j!=1)
                {
                    return j;
                }
            }
        }catch(FileNotFoundException e)
        {
            log.info(e);
        }
        catch (OfficeXmlFileException e)
        {
            log.info(e);
        }
        catch (POIXMLException e)
        {
            log.info(e);
            return 2;//文件格式不正确
        }
        catch (IOException e) {
            log.info(e);
        }
        return 1;
    }
}
