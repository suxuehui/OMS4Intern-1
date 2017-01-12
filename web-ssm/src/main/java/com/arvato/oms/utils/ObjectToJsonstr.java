package com.arvato.oms.utils;

import com.arvato.oms.controller.InboundorderController;
import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.OutboundorderModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by GONG036 on 2016/12/25.
 */
public class ObjectToJsonstr {
    Logger logger = Logger.getLogger(InboundorderController.class);
    //将母页面对象转json转字符串
    public String outobjtojson(Page pagelist, List<OutboundorderModel> list, String jsonstr){
        for(OutboundorderModel obolist1 : list ){
            //转换订单状态和出库单状态表现形式
             transferstatus(obolist1);
            System.out.print ("========================"+obolist1.getOrderstatus ());
        }

        JSONObject json = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }

    public  OutboundorderModel  transferstatus( OutboundorderModel  obolist1){
        /**
         * orderStatus：
         * 1-----------缺货异常 2-----------订单异常  3-----------待预检 4-----------待路由
         * 5-----------已出库 6-----------已完成 7-----------缺货等待
         * 8-----------待出库 9-----------已取消 10----------出库异常 11----------已发货
         * outboundState：
         * 1-----------已发货 2-----------处理中 3-----------仓库库存异常
         * 4-----------缺货5-----------已取消 6-----------补货成功 7-----------出库异常
         */
            int os= Integer.parseInt (obolist1.getOrderstatus ()) ;
            int obs= Integer.parseInt (obolist1.getOutboundstate ()) ;
            String[]a=new String[]{"","缺货异常","订单异常","待预检","待路由","已出库","已完成","缺货等待","待出库","已取消","出库异常","已发货"} ;
            String[]b=new String[]{"","已发货","处理中","仓库库存异常","缺货","已取消","补货成功","出库异常"} ;
            try{
                   obolist1.setOrderstatus(a[os]) ;

               } catch(Exception e){
                   logger.info("出库单的出库状态码数组有误"+e);
                   obolist1.setOrderstatus(a[0]);
               }
            try{
             obolist1.setOutboundstate(b[obs]) ;
            } catch(Exception e){
            logger.info("出库单的订单状态码数组有误"+e);
            obolist1.setOutboundstate(b[0]) ;
            }
            return obolist1;
    }

    //将子页面对象转json转字符串
    public String objtojson(Page pagelist, List<InboundorderModel> list, String jsonstr){
        for(InboundorderModel ibolist1 : list ){
            inboundtransferstatus(ibolist1);
        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }

    public InboundorderModel inboundtransferstatus(InboundorderModel ibolist1){
        /**
         * inboundState：1-----------接收成功 2-----------收货成功
         * 3-----------收货失败 4-----------超15天未收货 5-----------等待收货
         */
        int ios= Integer.parseInt (ibolist1.getInboundstate ()) ;
        String[]c=new String[]{"","接收成功","收货成功","收货失败","超15天未收货","等待收货"  } ;
        try{
            ibolist1.setInboundstate(c[ios]) ;
        } catch(Exception e){
            logger.info("入库单的入库状态码数组有误"+e);
            ibolist1.setInboundstate(c[0]);
        }
        return ibolist1;
    }

}
