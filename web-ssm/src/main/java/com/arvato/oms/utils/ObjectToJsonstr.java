package com.arvato.oms.utils;

import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.OutboundorderModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by GONG036 on 2016/12/25.
 */
public class ObjectToJsonstr {
    //将母页面对象转json转字符串
    public String outobjtojson(Page pagelist, List<OutboundorderModel> list, String jsonstr){
        /**
         *orderStatus：
         *1-----------缺货异常 2-----------订单异常  3-----------待预检
         *4-----------待路由 5-----------已出库  6-----------已完成
         *
         * outboundState：
         * 1-----------已发货 2-----------处理中 3-----------仓库库存异常
         */
       for(OutboundorderModel obolist1 : list ){
           int os= Integer.parseInt (obolist1.getOrderstatus ()) ;
           int obs= Integer.parseInt (obolist1.getOutboundstate ()) ;
           switch (os){
               case 1:
                   obolist1.setOrderstatus ("缺货异常");
                   break;
               case 2:
                   obolist1.setOrderstatus ("订单异常");
                   break;
               case 3:
                   obolist1.setOrderstatus ("待预检");
                   break;
               case 4:
                   obolist1.setOrderstatus ("待路由");
                   break;
               case 5:
                   obolist1.setOrderstatus ("已出库");
                   break;
               case 6:
                   obolist1.setOrderstatus ("已完成");
                   break;
               default:break;
           }
          if(obs==1){
              obolist1.setOutboundstate ("已发货");
          }
          else if(obs==2){
              obolist1.setOutboundstate ("处理中");
          }
          else{
              obolist1.setOutboundstate ("仓库库存异常");
          }
       }

        JSONObject json = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }



    //将子页面对象转json转字符串
    public String objtojson(Page pagelist, List<InboundorderModel> list, String jsonstr){
       /**
        * inboundState： 1-----------接收成功 2-----------收货成功
        *                3-----------收货失败 4-----------超15天未收货
        */
        for(InboundorderModel ibolist1 : list ){
            int ios= Integer.parseInt (ibolist1.getInboundstate ()) ;
            switch (ios){
                case 1:
                    ibolist1.setInboundstate ("接收成功");
                    break;
                case 2:
                    ibolist1.setInboundstate ("收货成功");
                    break;
                case 3:
                    ibolist1.setInboundstate ("收货失败");
                    break;
                case 4:
                    ibolist1.setInboundstate ("超15天未收货");
                    break;
                default:break;
            }
        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;
    }
}
