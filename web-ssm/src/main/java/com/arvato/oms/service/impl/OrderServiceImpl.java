package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.arvato.oms.ImportTradeModel.Order;
import com.arvato.oms.ImportTradeModel.Promotion_detail;
import com.arvato.oms.dao.*;
import com.arvato.oms.model.*;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.utils.HTTPClientDemo;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    //根据订单号分页查询商品信息
    public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    {
        JSONObject jObj=new JSONObject();
        int num=relationogModelMapper.selectCount(oId);
        int pageTotal=num/pageSize+1;
        int count=(pageNo-1)*pageSize;
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
        if(queryMode==1)//按订单号查询
        {
            int num=orderModelMapper.CountByOid(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByOids(count,pageSize,data);
        }
        else if (queryMode==2)//按渠道订单号查询
        {
            int num=orderModelMapper.CountByChanneloid(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByChanneloids(count,pageSize,data);
        }
        else if (queryMode==3)//按订单状态查询
        {
            int num=orderModelMapper.CountByOrderStatus(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByOrderStatuss(count,pageSize,data);
        }
        else if (queryMode==4)//按支付方式查询
        {
            int num=orderModelMapper.CountByPayStyle(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByPayStyles(count,pageSize,data);
        }
        else if (queryMode==5)//按物流公司查询
        {
            int num=orderModelMapper.CountByLogisticsCompany(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByLogisticsCompanys(count,pageSize,data);
        }
        else if (queryMode==6)//按省查询
        {
            int num=orderModelMapper.CountByReceiverProvince(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverProvinces(count,pageSize,data);
        }
        else if (queryMode==7)//按市查询
        {
            int num=orderModelMapper.CountByReceiverCity(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverCitys(count,pageSize,data);
        }
        else if (queryMode==8)//按区查询
        {
            int num=orderModelMapper.CountByReceiverArea(data);
            pageTotal=page.getTotalPageCount(num);
            orderModels=orderModelMapper.selectByReceiverAreas(count,pageSize,data);
        }
        else if (queryMode==9)//按收货人手机号查询
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
    public int updateByOidSelective(OrderModel record)
    {
        return orderModelMapper.updateByOidSelective(record);
    }
    //预检订单
    public int previewOrder(String oId, String exceptionType)
    {
        OrderModel orderModel=selectByOid(oId);
        List<String> exceptionList=Arrays.asList("商品异常","仓库库存异常","金额异常","备注异常");
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
        ExceptionModel exceptionModel=null;
        if(exceptionType==null||exceptionType.equals("商品异常"))
        {
            for(RelationogModel re:relationogModelList)
            {
                GoodsModel goodsModel=goodsModelMapper.selectByPrimaryKey(re.getGoodsno());
                if(goodsModel==null)
                {
                    exceptionModel=createException(orderModel);
                    exceptionModel.setExceptiontype("商品异常");
                    exceptionModel.setExpceptioncause("暂时缺货");
                    exceptionModel.setOrderstatus("订单异常");
                    exceptionModel.setOrderfrom("预检");
                    orderModel.setOrderstatus("订单异常");
                    orderModelMapper.updateByOidSelective(orderModel);
                    return exceptionModelMapper.insertSelective(exceptionModel);
                }
            }
        }
        if(exceptionType==null||exceptionType.equals("仓库库存异常"))
        {
            for(RelationogModel re:relationogModelList)
            {
                System.out.println(re.getGoodsno());
                GoodsModel goodsModel=goodsModelMapper.selectByPrimaryKey(re.getGoodsno());
                int goodsRnum=relationogModelMapper.selectGoodsRnum(oId);
                int goodsVnum=goodsModel.getGoodstolnum()-goodsRnum;
                System.out.println(goodsModel.toString());
                if(re.getGoodnum()>goodsVnum)
                {
                    exceptionModel=createException(orderModel);
                    exceptionModel.setExceptiontype("仓库库存异常");
                    exceptionModel.setExpceptioncause("库存不足");
                    exceptionModel.setOrderstatus("订单异常");
                    exceptionModel.setOrderfrom("预检");
                    orderModel.setOrderstatus("订单异常");
                    orderModelMapper.updateByOidSelective(orderModel);
                    return exceptionModelMapper.insertSelective(exceptionModel);
                }
            }
        }
        if(exceptionType==null||exceptionType.equals("金额异常"))
        {
            if(orderModel.getGoodstolprice().doubleValue()>10000)
            {
                exceptionModel=createException(orderModel);
                exceptionModel.setExceptiontype("金额异常");
                exceptionModel.setExpceptioncause("金额大于10000");
                exceptionModel.setOrderstatus("订单异常");
                exceptionModel.setOrderfrom("预检");
                orderModel.setOrderstatus("订单异常");
                orderModelMapper.updateByOidSelective(orderModel);
                return exceptionModelMapper.insertSelective(exceptionModel);
            }
        }
        if(exceptionType==null||exceptionType.equals("备注异常"))
        {
            if(orderModel.getRemark()!=null)
            {
                exceptionModel=createException(orderModel);
                exceptionModel.setExceptiontype("备注异常");
                exceptionModel.setExpceptioncause("订单有备注内容");
                exceptionModel.setOrderstatus("订单异常");
                exceptionModel.setOrderfrom("预检");
                orderModel.setOrderstatus("订单异常");
                orderModelMapper.updateByOidSelective(orderModel);
                return exceptionModelMapper.insertSelective(exceptionModel);
            }
        }
        //book库存
        for(RelationogModel re:relationogModelList)
        {
            int j=relationogModelMapper.updateGoodsRnum(re.getId(),1);
            if(j==0)
            {
                return 0;
            }
        }
        orderModel.setOrderstatus("待路由");
        int i=orderModelMapper.updateByOidSelective(orderModel);
        return i;
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
    public int cancleOrder(String oId)
    {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(oId==null)
        {
            return 2;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("已完成"))
        {
            return 3;//订单状态不符
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
                outboundorderModelMapper.updateByOidSelective(outboundorderModel);
            }
            orderModel.setOrderstatus("已取消");
            orderModelMapper.updateByOidSelective(orderModel);
            //取消该订单的锁定库存
            for(RelationogModel re:relationogModelList)
            {
                relationogModelMapper.updateGoodsRnum(re.getId(),0);
            }
        }
        if(orderModel.getOrderstatus().equals("已出库"))
        {
            HTTPClientDemo httpClientDemo=new HTTPClientDemo("url");
            String str=httpClientDemo.postMethod(oId);
        }
        return 0;
    }
    //路由
    public int routeOrder(String oId) {
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
        int i=orderModelMapper.updateByOidSelective(orderModel);
        if(i==1)
        {
            orderModel.setOrderstatus("待出库");
            orderModelMapper.updateByOidSelective(orderModel);
        }
        return i;
    }
    //生成出库单
    public int outboundOrder(String oId) {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(orderModel==null)
        {
            return 2;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("待出库"))
        {
            return 4;//订单状态不符
        }
        OutboundorderModel outboundorderModel=new OutboundorderModel();
        outboundorderModel.setOid(orderModel.getOid());
        outboundorderModel.setChanneloid(orderModel.getChanneloid());
        outboundorderModel.setOutboundid("SO"+orderModel.getOid());
        outboundorderModel.setSynchrostate(false);
        outboundorderModel.setReceivername(orderModel.getReceivername());
        outboundorderModel.setReceiveraddress(orderModel.getReceiverprovince()+orderModel.getReceivercity()+orderModel.getReceiverarea()+orderModel.getDetailaddress());
        outboundorderModel.setCreatedtime(new Date());
        int j=outboundorderModelMapper.insert(outboundorderModel);
        if(j==0)
        {
            return 0;//插入数据库出错
        }
        int i=sendOutboundOrder(orderModel,outboundorderModel);
        //释放锁定库存
        if(i==1)
        {
            List<RelationogModel> relationogModelList=relationogModelMapper.selectAllByOid(oId);
            for(RelationogModel re:relationogModelList)
            {
                relationogModelMapper.updateGoodsRnum(re.getId(),0);
            }
        }
        return i;
    }
    //调用WMS接口发送出库单
    public int sendOutboundOrder(OrderModel orderModel,OutboundorderModel outboundorderModel)
    {
        List<GoodsPojo> goodsPojoList=goodsModelMapper.selectAllByOid(orderModel.getOid());
        List<WMSOutBoundGoods> outBoundGoodsList=new ArrayList<WMSOutBoundGoods>();
        for(int i=0;i<goodsPojoList.size();i++)
        {
            WMSOutBoundGoods wmsGoods=outBoundGoodsList.get(i);
            GoodsPojo goods=goodsPojoList.get(i);
            wmsGoods.setSku(goods.getGoodsno());
            wmsGoods.setName(goods.getGoodsname());
            wmsGoods.setNum(goods.getGoodNum());
            wmsGoods.setPrice(goods.getGoodsprice());
            wmsGoods.setTotalprice(new BigDecimal(goods.getGoodNum()*(goods.getGoodsprice().doubleValue())));
            wmsGoods.setOutboundnum(goods.getGoodNum());
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("orderid",orderModel.getOid());
        jsonObject.put("outboundorderid",outboundorderModel.getOutboundid());
        jsonObject.put("channelorderid",outboundorderModel.getChanneloid());
        jsonObject.put("message",orderModel.getRemark());
        jsonObject.put("receiver",outboundorderModel.getReceivername());
        jsonObject.put("receivertel",orderModel.getReceivermobel());
        jsonObject.put("receiverAddress",outboundorderModel.getReceiveraddress());
        jsonObject.put("outboundordergoods",outBoundGoodsList);
        HTTPClientDemo httpClientDemo=new HTTPClientDemo("url");
        String response=httpClientDemo.postMethod(jsonObject.toString());
        if(response==null||!response.equals("ok"))
        {
            if(response==null)
            {
                response="没有收到wms响应";
            }
            ExceptionModel exceptionModel=createException(orderModel);
            exceptionModel.setExceptiontype("出库异常");
            exceptionModel.setExpceptioncause(response);
            exceptionModel.setOrderstatus("出库异常");
            exceptionModel.setOrderfrom("出库");
            orderModel.setOrderstatus("出库异常");
            orderModelMapper.updateByOidSelective(orderModel);
            int i=exceptionModelMapper.insertSelective(exceptionModel);
            if(i==0)
            {
                return 0;
            }
            return 3;//出库异常
        }
        else
        {
            orderModel.setOrderstatus("已出库");
            int i=orderModelMapper.updateByOidSelective(orderModel);
            outboundorderModel.setOrderstatus("已出库");
            outboundorderModel.setOutboundstate("处理中");
            int j=outboundorderModelMapper.updateByOidSelective(outboundorderModel);
            return i*j;
        }
    }
    //插入退换货单
    public int insertSelective(ReturnedModel record)
    {
        return returnedModelMapper.insertSelective(record);
    }
    //导入订单
    public int importOrder(String str) {
        JSONObject jsonObject= JSON.parseObject(str);
        JSONObject tradeJson=jsonObject.getJSONObject("trade_fullinfo_get_response").getJSONObject("trade");
        String promotion_details= tradeJson.getJSONObject("promotion_details").getString("promotion_detail");
        String orders= tradeJson.getJSONObject("orders").getString("order");
        ArrayList<Promotion_detail> promotion_detailList=JSON.parseObject(promotion_details,new TypeReference<ArrayList<Promotion_detail>>(){});
        ArrayList<Order> orderList=JSON.parseObject(orders,new TypeReference<ArrayList<Order>>(){});
        OrderModel orderModel=new OrderModel();
        RelationogModel relationogModel=new RelationogModel();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMDD");
        String oId="OO"+simpleDateFormat.format(new Date())+(int)(Math.random()*100000+10000);
        orderModel.setOid(oId);
        //判断该渠道订单是否已经存在
        OrderModel orderModel1=orderModelMapper.selectByChannelOid(tradeJson.getString("tid"));
        if(orderModel1!=null)
        {
            return 2;
        }
        orderModel.setChanneloid(tradeJson.getString("tid"));
        orderModel.setOrderstatus("待预检");
        orderModel.setOrderform("trade_from");
        orderModel.setBuyerid(tradeJson.getString("buyer_nick"));
        orderModel.setOrdertime(tradeJson.getDate("created"));
        orderModel.setBasestatus("活动");
        orderModel.setPaystatus("已支付");
        orderModel.setPaystyle("支付宝");
        orderModel.setPaytime(tradeJson.getDate("pay_time"));
        orderModel.setGoodstolprice(tradeJson.getBigDecimal("total_fee"));
        double discountPrice=0.00;
        for(Promotion_detail p:promotion_detailList)
        {
            discountPrice+=Double.parseDouble(p.getDiscount_fee());
        }
        orderModel.setDiscountprice(discountPrice);
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
        int j=orderModelMapper.insertSelective(orderModel);
        for(Order order:orderList)
        {
            relationogModel.setOid(oId);
            relationogModel.setGoodsno(order.getNum_iid());
            relationogModel.setGoodnum(order.getNum());
            relationogModel.setStatus((byte)0);
            int i=relationogModelMapper.insertSelective(relationogModel);
            if(i==0)
            {
                return 0;
            }
        }
        return j;
    }

    public int insertSelective(RelationrgModel record)
    {
        return relationrgModelMapper.insertSelective(record);
    }
}
