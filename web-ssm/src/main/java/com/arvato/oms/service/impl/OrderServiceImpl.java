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
    //根据订单号分页查询商品信息
    public JSONObject selectByOid(int pageNo,int pageSize,String oId)
    {
        JSONObject jObj=new JSONObject();
        int num=relationogModelMapper.selectCount(oId);
        Page page=new Page(num,pageNo,pageSize);
        int count=page.getStartPos();
        int pageTotal=page.getTotalPageCount();
        List<GoodsPojo> goodsPojos=goodsModelMapper.selectByOid(count,pageSize,oId);
        for(GoodsPojo d:goodsPojos)
        {
            System.out.println(d.toString());
        }
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
    public int updateByOidSelective(OrderModel record)
    {
        return orderModelMapper.updateByOidSelective(record);
    }
    //预检订单
    public int previewOrder(String oId, String exceptionType,String name)
    {
        OrderModel orderModel=selectByOid(oId);
        List<String> exceptionList=Arrays.asList("商品异常","仓库库存异常","金额异常","备注异常",null);
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
                    orderModel.setModifytime(new Date());
                    orderModel.setModifyman(name);
                    orderModelMapper.updateByOidSelective(orderModel);
                    exceptionModelMapper.insertSelective(exceptionModel);
                    return 5;
                }
            }
        }
        if(exceptionType==null||exceptionType.equals("仓库库存异常"))
        {
            for(RelationogModel re:relationogModelList)
            {
                System.out.println(re.getGoodsno());
                GoodsModel goodsModel=goodsModelMapper.selectByPrimaryKey(re.getGoodsno());
                int goodsRnum=0;
                if(relationogModelMapper.selectGoodsRnum(oId)==null)
                {
                    goodsRnum=5;
                }
                else
                {
                    goodsRnum =relationogModelMapper.selectGoodsRnum(oId);
                }
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
                    exceptionModelMapper.insertSelective(exceptionModel);
                    return 5;
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
                exceptionModelMapper.insertSelective(exceptionModel);
                return 5;
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
                exceptionModelMapper.insertSelective(exceptionModel);
                return 5;
            }
        }
        //book库存
        for(RelationogModel re:relationogModelList)
        {
            re.setStatus((byte)1);
            relationogModelMapper.updateByPrimaryKeySelective(re);
        }
        orderModel.setOrderstatus("待路由");
        orderModelMapper.updateByOidSelective(orderModel);
        return 1;
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
    public int cancleOrder(String oId,String uname)
    {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(oId==null)
        {
            return 2;//订单不存在
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
            HTTPClientDemo httpClientDemo=new HTTPClientDemo("http://114.215.252.146:8080/wms/outboundOrder/cancelOrder");
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
                    return 1;
                }
            }
            catch (Exception e)
            {
                return 3;//接口连接异常
            }
            if(code.equals("101"))
            {
                return 4;//订单已发货或已取消
            }
            if(code.equals("102"))
            {
                return 5;//无效的出库单号
            }
        }
        return 1;
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
    public int outboundOrder(String oId,String uname) {
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        if(orderModel==null)
        {
            return 2;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("待出库"))
        {
            return 3;//订单状态不符
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
        return sendOutboundOrder(orderModel,outboundorderModel,uname);
    }
    //调用WMS接口发送出库单
    public int sendOutboundOrder(OrderModel orderModel,OutboundorderModel outboundorderModel,String uname)
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
            wmsGoods.setTotalprice(new BigDecimal(goods.getGoodNum()*(goods.getGoodsprice().doubleValue())));
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
        HTTPClientDemo httpClientDemo=new HTTPClientDemo("http://114.215.252.146:8080/wms/outboundOrder/receiveOrder");
        String response=httpClientDemo.postMethod(jsonObject.toString());
        String code="";
        int cause=0;
        try {
            code = JSON.parseObject(response).getString("code");
        }
        catch(Exception e)
        {
            cause=4;//"接口连接异常";
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
            cause=5;//"数据格式错误";
            code="数据格式错误";
        }
        if(code.equals("102"))
        {
            cause=6;//"重复的出库单";
            code="重复的出库单";
        }
        ExceptionModel exceptionModel=createException(orderModel);
        exceptionModel.setExceptiontype("出库异常");
        exceptionModel.setExpceptioncause(code);
        exceptionModel.setOrderstatus("出库异常");
        exceptionModel.setOrderfrom("出库");
        exceptionModelMapper.insertSelective(exceptionModel);
        outboundorderModel.setOrderstatus("出库异常");
        outboundorderModel.setOutboundstate("出库异常");
        outboundorderModel.setModifytime(new Date());
        outboundorderModel.setModifyman(uname);
        outboundorderModelMapper.updateByOidSelective(outboundorderModel);
        orderModel.setOrderstatus("出库异常");
        orderModel.setModifytime(new Date());
        orderModel.setModifyman(uname);
        orderModelMapper.updateByOidSelective(orderModel);
        return cause;
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
        GoodsModel goodsModel=new GoodsModel();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMdd");
        String oId="OO"+simpleDateFormat.format(new Date())+(int)(Math.random()*90000+10000);
        orderModel.setOid(oId);
        //判断该渠道订单是否已经存在
        OrderModel orderModel1=orderModelMapper.selectByChannelOid(tradeJson.getString("tid"));
        if(orderModel1!=null)
        {
            return 2;
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
        orderModel.setBuyeralipayno(tradeJson.getString("buyer_alipay_no"));
        int j=orderModelMapper.insertSelective(orderModel);
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
                divideorderfee=new BigDecimal((totalfee.doubleValue())/(order.getNum()));
            }
            relationogModel.setDivideorderfee(divideorderfee);
            relationogModel.setTotalfee(totalfee);
            relationogModelMapper.insertSelective(relationogModel);
        }
        return j;
    }

    //退换货
    public int returnGoods(String jsonStr)
    {
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        ArrayList<GoodsPojo> goodsList=JSON.parseObject(jsonObject.getString("goods"),new TypeReference<ArrayList<GoodsPojo>>(){});
        OrderModel orderModel=orderModelMapper.selectByOid(jsonObject.getString("oid"));
        RelationrgModel[] relationRgModels=new RelationrgModel[goodsList.size()];
        ReturnedModel returnedModel=returnedModelMapper.selectByOid(orderModel.getOid());
        if(returnedModel!=null&&!returnedModel.getReturnedstatus().equals("0"))
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
            return 0;//订单未完成
        }
        if(goodsList.size()==0)
        {
            return 0;//没有选择商品
        }
        returnedModel.setChanneloid(orderModel.getChanneloid());
        returnedModel.setReturnedid("RT"+jsonObject.getString("oid")+(int)(Math.random()*90000+10000));
        returnedModel.setReturnedorchange(jsonObject.getString("returnedOrChange"));
        returnedModel.setBuyeralipayno(orderModel.getBuyeralipayno());
        double returnedMoney=0.00;
        for(int i=0;i<goodsList.size();i++)
        {
            returnedMoney+=goodsList.get(i).getGoodNum()*(goodsList.get(i).getDivideorderfee()).doubleValue();
            relationRgModels[i]=new RelationrgModel();
            relationRgModels[i].setReturnedid("RT"+jsonObject.getString("oid"));
            relationRgModels[i].setGoodsno(goodsList.get(i).getGoodsno());
            relationRgModels[i].setGoodnum(goodsList.get(i).getGoodNum());
        }
        returnedModel.setReturnedmoney(new BigDecimal(returnedMoney));
        returnedModel.setReturnedstatus("待审核");
        Date date=new Date();
        returnedModel.setCreatetime(date);
        int i=returnedModelMapper.insertSelective(returnedModel);
        if(i==0)
        {
            return 0;
        }
        for(RelationrgModel re:relationRgModels)
        {
            int j=relationrgModelMapper.insertSelective(re);
            if(j==0)
            {
                return 0;
            }
        }
        return 1;
    }
    //检查订单是否可以退换货
    public int checkreturn(String oid)
    {
        ReturnedModel returnedModel=returnedModelMapper.selectByOid(oid);
        if(returnedModel==null||returnedModel.getReturnedstatus().equals("0"))
        {
            return 0;//可以退换货
        }
        return 1;//不能退换货操作
    }

    //更新订单列表的订单状态
    public void updateOrder(String orderStatus, String oid) {
        orderModelMapper.updateOrder(orderStatus,oid);
    }

    //根据订单号查询该条退货单记录
    public OrderModel selectByoId(String oId) {
        return orderModelMapper.selectByoId(oId);
    }

}
