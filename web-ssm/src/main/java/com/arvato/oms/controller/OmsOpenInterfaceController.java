package com.arvato.oms.controller;

import com.arvato.oms.service.GoodsModelService;
import com.arvato.oms.service.InboundorderService;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.service.OutboundorderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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


    //接受wms传来的数据，更新出库单
    @RequestMapping(value = "updateOutboundOrder")
    @ResponseBody
    public String updateOutboundOrder(@RequestBody String updateOutboundOrder_message, HttpServletRequest request){
        //获得从client传来的json对象
        JSONObject object = JSONObject.fromObject(updateOutboundOrder_message);
        if(object==null){
            return "{\"msg\":\"101\"}";//未获得从client传来的json对象
        }
        else
        {
            System.out.println(object);
            //获得出库更新信息数组对象
            JSONObject outbounds=object.getJSONObject("outbound_update_message").getJSONObject("outbounds");
            if(outbounds==null){
                return "{\"msg\":\"102\"}";//未获得出库更新信息数组对象outbounds
            }
            else
            {   //获得出库更新信息数组
                JSONArray outbound =outbounds.getJSONArray("outbound");
                if(outbound==null){
                    return "{\"msg\":\"103\"}";//未获得出库更新信息outbound
                }
                else
                {
                    for (int i = 0; i < outbound.size(); i++) {
                        //仓库出库单号
                        String warehouseObid = outbound.getJSONObject(i).getString("warehouseObid");
                        if(warehouseObid==null){
                            return "{\"msg\":\"301\"}";//仓库出库单号不能为空
                        }
                        if (warehouseObid.length()!=17||!"WO".equals(warehouseObid.substring(0,2))){
                        //WO+15位订单号
                            return "{\"msg\":\"301\"}";//仓库出库单号格式错误
                        }
                        //出库单号
                        String outboundId = outbound.getJSONObject(i).getString("outboundId");
                        if(outboundId==null){
                            return "{\"msg\":\"301\"}";//出库单号不能为空
                        }
                        if(outboundId.length()!=17||!"SO".equals(outboundId.substring(0,2))){
                         //SO+15位订单号
                            return "{\"msg\":\"301\"}";//出库单号格式错误
                        }
                        //出库单状态
                        String outboundState = outbound.getJSONObject(i).getString("outboundState");
                        if(outboundState==null){
                            return "{\"msg\":\"305\"}";//出库单状态不能为空
                        }
                        if("已出库".equals(outboundState)){
                            return "{\"msg\":\"306\"}";//出库单状态错误
                        }
                        //快递公司
                        String expressCompany = outbound.getJSONObject(i).getString("expressCompany");
                        if(expressCompany==null){
                            return "{\"msg\":\"307\"}";//快递公司不能为空
                        }
                        //快递单号
                        String expressId = outbound.getJSONObject(i).getString("expressId");
                        if(expressId==null){
                            return "{\"msg\":\"308\"}";//快递单号不能为空
                        }
                        String orderStatus="已发货";
                        //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
                        outboundServiceImpl.updateOutboundorder(orderStatus,outboundState,warehouseObid,expressCompany,expressId,outboundId);
                        //先从出库表获取订单号，然后更新订单列表的订单状态
                        String oid = outboundServiceImpl.selectOidByOutboundId(outboundId);
                        orderServiceImpl.updateOrder(orderStatus,oid);

                    }
                }
            }
        }

        return "{\"msg\":\"100\"}";//成功
    }

    //接受wms传来的数据，更新入库单
    @RequestMapping(value = "updateInboundOrder")
    @ResponseBody
    public String updateInboundOrder(@RequestBody String message) {
        int s = 0;
        try {
            JSONObject object = JSONObject.fromObject(message);//获得从client传来的json对象
            String status_codes = object.getJSONObject("inbound_update_message").getString("status_codes"); //获得状态码
            JSONObject inbounds = object.getJSONObject("inbound_update_message").getJSONObject("inbounds"); //获得入库更新信息数组
            JSONArray inbound = inbounds.getJSONArray("inbound");
            Map<String, Object> map = new HashMap<String, Object>();
            int code = Integer.parseInt(status_codes);
            if (code == 1) {
                //向数据库写入数据
                for (int i = 0; i < inbound.size(); i++) {
                    String inboundId = inbound.getJSONObject(i).getString("inboundId");
                    String inboundState = inbound.getJSONObject(i).getString("inboundState");
                    s = inboundorderserviceimpl.updateByInboundId(inboundId, inboundState);
                    if (s == 0) {
                        return "{\"status_codes\":000,\"msg\":\"参数的数据格式有误\",\"body\":\"入库单更新失败\"}";
                    }
                }
            }
        } catch (Exception e) {//异常的捕获
            return "{\"status_codes\":111,\"msg\":\"json字符串格式有误\",\"body\":\"入库单数据参数接收失败\"}";
        }
        return "{\"status_codes\":222,\"msg\":\"入库单状态更新成功\",\"body\":\"入库单状态更新成功\"}";
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
            System.out.println(object);
            //商品操作，是上架还是下架
            String operation = object.getString("operation");
            System.out.println(operation);
            if(operation.equals("add")){
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
                String goodsTolnum = goods.getString("goodsTolnum");
                if(goodsTolnum==null){
                    return "{\"msg\":\"105\"}";//未获得商品总库存
                }
                //商品状态
                String goodsState = goods.getString("goodsState");
                if(goodsState==null){
                    return "{\"msg\":\"106\"}";//未获得商品状态
                }
                //可用库存，商品刚上架是可用库存等于总库存
                String goodsVnum = goods.getString("goodsTolnum");
                //添加一条商品信息
                goodsServiceImpl.addGoods(goodsNo,goodsName,goodsVnum,goodsPrice,goodsTolnum,goodsState);

            }else{
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
                //已下架直接将其商品状态改为"已下架"
                goodsServiceImpl.updateGoodsState(goodsState2,goodsNo2);
            }

        }
        return "{\"msg\":\"100\"}";//成功
    }

}
