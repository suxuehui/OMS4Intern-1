package com.arvato.oms.controller;

import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZHOU169 on 2016/12/16.
 */
@Controller
@RequestMapping("/OmsOpenInterface/")
public class OmsOpenInterfaceController {
    @Resource
    private OutboundorderService outboundServiceImpl;
    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private GoodsModelService goodsServiceImpl;
    @Resource
    private InboundorderService inboundorderserviceimpl;
    @Resource
    private ExceptionService exceptionServiceImpl;
    @Resource
    private RelationogService relationogServiceImpl;
    @Resource
    private ReturnedModelService returnedModelService;

    private static final String GTN = "goodsTolnum";

    //接受wms传来的数据，更新出库单
    @RequestMapping(value = "updateOutboundOrder")
    @ResponseBody
    public String updateOutboundOrder(@RequestBody String updateOutboundOrder_message, HttpSession session){
        String userName = (String)session.getAttribute("uname");
        //获得从client传来的json对象
        JSONObject object = JSONObject.fromObject(updateOutboundOrder_message);
        if(object==null){//未获得从client传来的json对象
            return "{\"msg\":\"200\"}";
        }
        JSONObject  outboundMessage= object.getJSONObject("outbound_message");
        if(outboundMessage==null){//未获得outboundMessage对象
            return "{\"msg\":\"201\"}";
        }
        //仓库出库单号
        String warehouseObid=outboundMessage.getString("warehouseObid");
        if(warehouseObid==null||"".equals(warehouseObid)){
            return "{\"msg\":\"301\"}";//仓库出库单号不能为空
        }
        if (warehouseObid.length()!=22||!"WO".equals(warehouseObid.substring(0,2))){
            //WO+15位订单号+5位随机数
            return "{\"msg\":\"302\"}";//仓库出库单号格式错误
        }
        //出库单号
        String outboundId =outboundMessage.getString("outboundId");
        if(outboundId==null||"".equals(outboundId)){
            return "{\"msg\":\"303\"}";//出库单号不能为空
        }
        if(outboundId.length()!=22||!"SO".equals(outboundId.substring(0,2))){
            //SO+15位订单号+5为随机数
            return "{\"msg\":\"304\"}";//出库单号格式错误
        }
        //出库单状态
        String outboundState = outboundMessage.getString("outboundState");
        if(outboundState==null||"".equals(outboundState)){
            return "{\"msg\":\"305\"}";//出库单状态不能为空
        }
        if("1".equals(outboundState)){//已发货
            //快递公司
            String expressCompany =outboundMessage.getString("expressCompany");
            if(expressCompany==null||"".equals(expressCompany)){
                return "{\"msg\":\"306\"}";//快递公司不能为空
            }
            //快递单号
            String expressId = outboundMessage.getString("expressId");
            if(expressId==null||"".equals(expressId)){
                return "{\"msg\":\"307\"}";//快递单号不能为空
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(expressId);
            if( !isNum.matches() ){
                return "{\"msg\":\"309\"}";//快递单号格式错误
            }
            if(expressId.length()<10||expressId.length()>16){
                return "{\"msg\":\"309\"}";//快递单号格式错误
            }
            String orderStatus="11";//已发货
            //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
            outboundServiceImpl.updateOutboundorder(orderStatus,outboundState,warehouseObid,expressCompany,expressId,new Date(),userName,outboundId);
            //先从出库表获取订单号，然后更新订单列表的订单状态
            String oid = outboundServiceImpl.selectOidByOutboundId(outboundId);
            orderServiceImpl.updateOrder(orderStatus,new Date(),userName,expressCompany,expressId,oid);
        }else if("2".equals(outboundState)){//缺货
            String orderStatus="1";//缺货异常
            String outboundState2="3";//仓库库存异常
            String expressCompany2=null;
            String expressId2=null;
            //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
            outboundServiceImpl.updateOutboundorder(orderStatus,outboundState2,warehouseObid,expressCompany2,expressId2,new Date(),userName,outboundId);
            //先从出库表获取订单号，然后更新订单列表的订单状态
            String oid = outboundServiceImpl.selectOidByOutboundId(outboundId);
            orderServiceImpl.updateOrder(orderStatus,new Date(),userName,expressCompany2,expressId2,oid);
            //获取该条订单
            OrderModel orderModel = orderServiceImpl.selectByOid(oid);
            //将该异常订单推到异常订单列表
            ExceptionModel exceptionModel = new ExceptionModel();
            exceptionModel.setOid(orderModel.getOid());
            exceptionModel.setChanneloid(orderModel.getChanneloid());
            exceptionModel.setOrderstatus(orderModel.getOrderstatus());
            exceptionModel.setOrderfrom("2");//WMS缺货
            exceptionModel.setExceptiontype("6");//仓库库存异常
            exceptionModel.setExpceptioncause("6");//库存不足
            exceptionModel.setExceptionstatus("1");//待处理
            exceptionModel.setCreatetime(new Date());
            exceptionServiceImpl.insertSelective(exceptionModel);

        }else if("3".equals(outboundState)){//已取消
            String orderStatus="9";//已取消
            String expressCompany3=null;
            String expressId3=null;
            //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
            outboundServiceImpl.updateOutboundorder(orderStatus,outboundState,warehouseObid,expressCompany3,expressId3,new Date(),userName,outboundId);
            //先从出库表获取订单号，然后更新订单列表的订单状态
            String oid = outboundServiceImpl.selectOidByOutboundId(outboundId);
            orderServiceImpl.updateOrder(orderStatus,new Date(),userName,expressCompany3,expressId3,oid);
            //生成退款单
            orderServiceImpl.createReturned(oid);
        }else if ("4".equals(outboundState)){//补货成功
            String orderStatus2="5";//已出库
            //快递公司
            String expressCompany4 =null;
            //快递单号
            String expressId4 = null;
            //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
            outboundServiceImpl.updateOutboundorder(orderStatus2,outboundState,warehouseObid,expressCompany4,expressId4,new Date(),userName,outboundId);
            //先从出库表获取订单号，然后更新订单列表的订单状态
            String oid2 = outboundServiceImpl.selectOidByOutboundId(outboundId);
            //更新订单列表订单状态
            orderServiceImpl.updateOrder(orderStatus2,new Date(),userName,expressCompany4,expressId4,oid2);
            //在异常列表中删除改异常订单
            exceptionServiceImpl.deleteByOid(oid2);

        }else{
            return "{\"msg\":\"308\"}";//出库单状态错误
        }
        return "{\"msg\":\"100\"}";//成功
    }

    //接受wms传来的数据，更新商品信息
    @RequestMapping(value = "updateGoods")
    @ResponseBody
    public String updateGoods(@RequestBody String updateGoods_message, HttpServletRequest request){
        //获得从client传来的json对象
        JSONObject object = JSONObject.fromObject(updateGoods_message);
        if(object==null){//未获得从client传来的json对象
            return "{\"msg\":\"101\"}";
        }
        else
        {
            //商品操作，是上架还是下架
            String operation = object.getString("operation");
            if("add".equals(operation)){
                JSONObject goods = object.getJSONObject("goods");
                //商品编号
                String goodsNo = goods.getString("goodsNo");
                if(goodsNo==null){
                    return "{\"msg\":\"102\"}";//未获得商品编号
                }
                //商品名称
                String goodsName = goods.getString("goodsName");
                if(goodsName==null){
                    return "{\"msg\":\"103\"}";//未获得商品名称
                }
                //商品单价
                String goodsPrice = goods.getString("goodsPrice");
                if(goodsPrice==null){
                    return "{\"msg\":\"104\"}";//未获得商品单价
                }
                //商品总库存
                String goodsTolnum = goods.getString(GTN);
                if(goodsTolnum==null){
                    return "{\"msg\":\"105\"}";//未获得商品总库存
                }
                //商品状态
                String goodsState = goods.getString("goodsState");
                if(goodsState==null){
                    return "{\"msg\":\"106\"}";//未获得商品状态
                }
                //可用库存，商品刚上架是可用库存等于总库存
                String goodsVnum = goods.getString(GTN);
                //添加一条商品信息
                goodsServiceImpl.addGoods(goodsNo,goodsName,goodsVnum,goodsPrice,goodsTolnum,goodsState);

            }else if("edit".equals(operation)){
                JSONObject goods = object.getJSONObject("goods");
                //商品编号
                String goodsNo2 = goods.getString("goodsNo");
                if(goodsNo2==null){
                    return "{\"msg\":\"102\"}";//未获得商品编号
                }
                //商品状态
                String goodsState2 = goods.getString("goodsState");
                if(goodsState2==null){
                    return "{\"msg\":\"106\"}";//未获得商品状态
                }
                //商品总库存
                String goodsTolnum2 = goods.getString(GTN);
                if(goodsTolnum2==null){
                    return "{\"msg\":\"105\"}";//未获得商品总库存
                }
                Integer goodsNum = relationogServiceImpl.countGoodsNum(goodsNo2);
                if(goodsNum>0)
                {
                    //计算锁定库存
                    Integer lockSum = relationogServiceImpl.selectGoodsRnum(goodsNo2);
                    if(lockSum==null){
                        lockSum=0;
                    }
                    //计算可用库存
                    Integer goodsvnum =  Integer.parseInt(goodsTolnum2)-lockSum.intValue();
                    //修改其商品状态,库存,可用库存
                    goodsServiceImpl.updateGoodsState(goodsState2,goodsTolnum2,Integer.toString(goodsvnum),goodsNo2);
                }
                else
                {
                    //修改其商品状态,库存,可用库存
                    goodsServiceImpl.updateGoodsState(goodsState2,goodsTolnum2,goodsTolnum2,goodsNo2);
                }
            }

        }
        return "{\"msg\":\"100\"}";//成功
    }
    
    //接受wms传来的数据，更新入库单
    @RequestMapping(value = "updateInboundOrder")
    @ResponseBody
    public String updateInboundOrder(@RequestBody String message,HttpSession session) {
        int s = 0;
        int updateReturnedOrderSign = 0;
        String modifyman = (String)session.getAttribute("uname");
        try {
            String str=telldata( s, updateReturnedOrderSign, message, modifyman);
            return str;
        } catch (Exception e) {//异常的捕获
            Logger logger = Logger.getLogger(OmsOpenInterfaceController.class);
            logger.info("入库单更新失败"+e);
            return "{\"status_codes\":111,\"msg\":\"json字符串格式有误\",\"body\":\"入库单数据参数接收失败\"}";
        }
    }

//将入库单的数据更新的方法
    public String  telldata(int s, int updateReturnedOrderSign,String message,String modifyman){
        JSONObject object = JSONObject.fromObject(message);//获得从client传来的json对象
        String status_codes = object.getJSONObject("inbound_update_message").getString("status_codes"); //获得状态码
        JSONObject inbounds = object.getJSONObject("inbound_update_message").getJSONObject("inbounds"); //获得入库更新信息数组
        JSONArray inbound = inbounds.getJSONArray("inbound");
        int code = Integer.parseInt(status_codes);
        if (code == 1) {
            //向数据库写入数据
            for (int i = 0; i < inbound.size(); i++) {
                String inboundId = inbound.getJSONObject(i).getString("inboundId");
                String inboundState = inbound.getJSONObject(i).getString("inboundState");
                Date date=new Date();
                s = inboundorderserviceimpl.updateByInboundId(inboundId, inboundState,date,modifyman);

                updateReturnedOrderSign = returnedModelService.updateReturnedStateByIid(inboundId,inboundState,date,modifyman);
                //判断传来的数据是否不合法
                if (s == 0 || updateReturnedOrderSign==0) {
                    return "{\"status_codes\":000,\"msg\":\"参数的数据格式有误\",\"body\":\"入库单更新失败\"}";
                }

            }
            return "{\"status_codes\":222,\"msg\":\"入库单状态更新成功\",\"body\":\"入库单状态更新成功\"}";
        }
        else{
            return "{\"status_codes\":111,\"msg\":\"json字符串格式有误\",\"body\":\"入库单数据参数接收失败\"}";
        }
    }
}
