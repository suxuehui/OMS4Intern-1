package com.arvato.oms.utils;

import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.model.RefoundOrderModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by ZHOU169 on 2017/1/12.
 */
public class ExcToJson {
    //将母页面对象转json转字符串
    public String excobjtojson(Page pagelist, List<ExceptionModel> list){
        /**
         *orderStatus：
         *1-----------缺货异常  2-----------订单异常  3-----------待预检
         *4-----------待路由    5-----------已出库    6-----------已完成
         *7-----------缺货等待  8-----------待出库    9----------已取消
         *10----------出库异常  11----------已发货
         *
         * exceptionType：
         *1-----------商品异常-----------该订单包含不存在或已下架的商品
         *2-----------库存异常-----------库存不足
         *3-----------金额异常-----------金额大于10000
         *4-----------备注异常-----------订单有备注内容
         *5-----------出库异常-----------通过接口返回的code得到
         *6-----------仓库库存异常-------库存不足
         *
         * exceptionStatus：
         * 1-----------待处理
         *
         * exception表中（实际应该是异常来源）：orderFrom：
         *1-----------预检
         *2-----------wms缺货
         *3-----------出库
         *
         */
        for(ExceptionModel obolist1 : list ){
            int os= Integer.parseInt (obolist1.getOrderstatus()) ;
            int et= Integer.parseInt (obolist1.getExceptiontype()) ;
            int of = Integer.parseInt (obolist1.getOrderfrom()) ;
            int es = Integer.parseInt (obolist1.getExceptionstatus()) ;
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
                case 7:
                    obolist1.setOrderstatus ("缺货等待");
                    break;
                case 8:
                    obolist1.setOrderstatus ("待出库");
                    break;
                case 9:
                    obolist1.setOrderstatus ("已取消");
                    break;
                case 10:
                    obolist1.setOrderstatus ("出库异常");
                    break;
                case 11:
                    obolist1.setOrderstatus ("已发货");
                    break;
                default:break;
            }
            switch (et){
                case 1:
                    obolist1.setExceptiontype("商品异常");
                    obolist1.setExpceptioncause("该订单包含不存在或已下架的商品");
                    break;
                case 2:
                    obolist1.setExceptiontype("库存异常");
                    obolist1.setExpceptioncause("库存不足");
                    break;
                case 3:
                    obolist1.setExceptiontype("金额异常");
                    obolist1.setExpceptioncause("金额大于10000");
                    break;
                case 4:
                    obolist1.setExceptiontype("备注异常");
                    obolist1.setExpceptioncause("订单有备注内容");
                    break;
                case 5:
                    obolist1.setExceptiontype("出库异常");
                    obolist1.setExpceptioncause("通过接口返回的code得到");
                    break;
                case 6:
                    obolist1.setExceptiontype("仓库库存异常");
                    obolist1.setExpceptioncause("库存不足");
                    break;
                default:break;
            }
            switch (of){
                case 1:
                    obolist1.setOrderfrom("预检");
                    break;
                case 2:
                    obolist1.setOrderfrom("wms缺货");
                    break;
                case 3:
                    obolist1.setOrderfrom("出库");
                    break;
                default:break;
            }
            if(es==1){
                obolist1.setExceptionstatus("待处理");
            }
        }

        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr ;
    }



    //将子页面对象转json转字符串
    public String refobjtojson(Page pagelist, List<RefoundOrderModel> list){
        /**
         * drawbackStatus：
         * 1-----------待退款
         * 2-----------退款失败
         * 3-----------退款成功
         */
        for(RefoundOrderModel ibolist1 : list ){
            int ds= Integer.parseInt (ibolist1.getDrawbackstatus()) ;
            switch (ds){
                case 1:
                    ibolist1.setDrawbackstatus("待退款");
                    break;
                case 2:
                    ibolist1.setDrawbackstatus("退款失败");
                    break;
                case 3:
                    ibolist1.setDrawbackstatus("退款成功");
                    break;
                default:break;
            }
        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr ;
    }
}
