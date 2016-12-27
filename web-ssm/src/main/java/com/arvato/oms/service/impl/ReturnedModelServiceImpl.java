package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.*;
import com.arvato.oms.model.*;
import com.arvato.oms.model.pojo.CodeRoot;
import com.arvato.oms.model.pojo.InboundInterface.InBoundRoot;
import com.arvato.oms.model.pojo.InboundInterface.Inbounddeliveryorder;
import com.arvato.oms.model.pojo.InboundInterface.Inboundordergoods;
import com.arvato.oms.model.pojo.OutBoundInterface.DeliveryOrder;
import com.arvato.oms.model.pojo.OutBoundInterface.OutBoundRoot;
import com.arvato.oms.model.pojo.OutBoundInterface.Outboundordergoods;
import com.arvato.oms.model.son.ReturnedSon;
import com.arvato.oms.service.ReturnedModelService;
import com.arvato.oms.utils.HTTPClientDemo;
import com.arvato.oms.utils.Page;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnedModelServiceImpl implements ReturnedModelService
{
    @Resource
    private ReturnedModelMapper returnedModelMapper;

    @Resource
    RelationrgModelMapper relationrgModelMapper;

    @Resource
    GoodsModelMapper goodsModelMapper;

    @Resource
    RefoundOrderModelMapper refoundOrderModelMapper;

    @Resource
    OrderModelMapper orderModelMapper;

    @Resource
    OutboundorderModelMapper outboundorderModelMapper;

    @Resource
    InboundorderModelMapper inboundorderModelMapper;

    private Logger log = Logger.getLogger(ReturnedModelServiceImpl.class);

    public int cancelReturn(Integer id)
    {
        /**
         * @Author: 马潇霄
         * @Description: 批量取消订单
         * @Date: 13:52 2016/12/9
         * @param ids 需要取消的订单号
         * @Return: int 取消成功条数
         */
        ReturnedModel returnedModel = returnedModelMapper.selectByPrimaryKey(id);
        String returnedOrChange = returnedModel.getReturnedorchange();
        if (returnedOrChange.equals("return")){
            return returnedModelMapper.updateStatusToDisable(id,"取消退货");

        }else if (returnedOrChange.equals("change")){
            return returnedModelMapper.updateStatusToDisable(id,"取消换货");
        }else {
            return 0;
        }


    }

    public JSONObject getAllReturnedOrders(int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取全部退款单信息
         * @Date: 15:09 2016/12/9
         * @param pageNow 当前页数
         * @param num 一页显示数据条数
         * @Return: JSONObject：returnedModels，totalPage，size
         */
        int countReturnedOrders = returnedModelMapper.countReturnedOrders();
        log.info(countReturnedOrders);
        Page page = new Page(countReturnedOrders, pageNow, num);
        List<ReturnedModel> returnedModels = returnedModelMapper.selectAllReturnedByPage(page.getStartPos(), num);
        JSONObject json = new JSONObject();
        json.put("returnedModels", returnedModels);
        json.put("totalPage", page.getTotalPageCount());
        json.put("size", returnedModels.size());
        json.put("getStartPos", page.getStartPos());

        return json;
    }

    public JSONObject getGoodsListByRid(String returnedId, int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据退货单编码查询商品信息
         * @Date: 10:58 2016/12/12
         * @param returnedId 退货单编码
         * @param pageNow 当前页
         * @param num 一页显示的数目
         * @Return: JSONObject：returnedSonList，pageSize
         */
        int i1 = relationrgModelMapper.countGoods(returnedId);
        Page page = new Page(i1, pageNow, num);
        log.info("getStartPos" + page.getStartPos());
        log.info("countGoods" + i1);
        List<RelationrgModel> relationrgModels = relationrgModelMapper.selectGoodsByRid(returnedId, page.getStartPos(), num);
        log.info("relationrgModels.size()" + relationrgModels.size());
        List<ReturnedSon> returnedSonList = new ArrayList<ReturnedSon>();
        for (int i = 0; i < relationrgModels.size(); i++)
        {
            ReturnedSon returnedSon = new ReturnedSon();
            RelationrgModel relationrgModel = relationrgModels.get(i);
            returnedSon.setGoodnum(relationrgModel.getGoodnum());
            returnedSon.setGoodsno(relationrgModel.getGoodsno());
            GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(returnedSon.getGoodsno());
            returnedSon.setGoodsname(goodsModel.getGoodsname());
            returnedSon.setGoodsprice(goodsModel.getGoodsprice());
            returnedSonList.add(returnedSon);

        }
        JSONObject json = new JSONObject();
        json.put("returnedSonList", returnedSonList);

        json.put("pageSize", page.getPageSize());
        json.put("totalPageCount", page.getTotalPageCount());
        json.put("pageNow", page.getPageNow());
        json.put("startPos", page.getStartPos());
        json.put("isHasFirst", page.isHasFirst());
        json.put("isHasLast", page.isHasLast());
        json.put("isHasNext", page.isHasNext());
        json.put("isHasPre", page.isHasPre());
        json.put("start", page.getStartPos());
        return json;
    }

    public JSONObject getReturnedListBySelect(String select, String value, int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据条件分页模糊查询退货单信息
         * @Date: 16:22 2016/12/12
         * @param select 条件类型
         * @param value 条件内容
         * @param pageNow 当前页码
         * @param num 每页显示查询条数
         * @Return: JSONObject
         */
        List<ReturnedModel> returnedModels = new ArrayList<ReturnedModel>();
        Page page = new Page(pageNow, num);
        if (select.equals("1"))
        {
            returnedModels = returnedModelMapper.selectReturnedById(page.getStartPos(), num, value);
        } else if (select.equals("2"))
        {
            returnedModels = returnedModelMapper.selectReturnedByOId(page.getStartPos(), num, value);
        } else if (select.equals("3"))
        {
            returnedModels = returnedModelMapper.selectReturnedByStatus(page.getStartPos(), num, value);
        } else if (select.equals("4"))
        {
            returnedModels = returnedModelMapper.selectReturnedByChannelOid(page.getStartPos(), num, value);
        }
        JSONObject json = new JSONObject();
        json.put("returnedModels", returnedModels);
        return json;
    }

    public int createRefoundOrders(List<String> returnedIds)
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建退款单
         * @Date: 11:19 2016/12/13
         * @param returnedIds 退货单号
         * @Return: int 创建条数
         */
        int j = 0;
        for (int i = 0; i < returnedIds.size(); i++)
        {

            ReturnedModel returnedModel = returnedModelMapper.selectOneReturnedById(returnedIds.get(i));
            RefoundOrderModel refoundOrderModel = new RefoundOrderModel();
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            refoundOrderModel.setCreatetime(date);//退款单创建时间
            String oid = returnedModel.getOid();
            String refoundedId = "RF" + oid + generateRandomNumber(2);
            refoundOrderModel.setDrawbackid(refoundedId);//退款单号
            refoundOrderModel.setDrawbackmoney(returnedModel.getReturnedmoney());//退款金额
            refoundOrderModel.setDrawbackstatus("0");//退款单状态
            refoundOrderModel.setReturnedid(returnedIds.get(i));//退货单号
            j = refoundOrderModelMapper.insertSelective(refoundOrderModel) + j;
        }

        return j;
    }

    public String createOutboundOrders(String returnedId)
    {

        /**
         * @Author: 马潇霄
         * @Description: 创建出库单（同步状态不确定），修改人缺省
         * @Date: 10:52 2016/12/15
         * @param returnedIds
         * @Return:
         *
         */
        ReturnedModel returnedModel = returnedModelMapper.selectOneReturnedById(returnedId);
        String returnedstatus = returnedModel.getReturnedstatus();
        if (returnedstatus.equals("收货成功"))
        {
            OutboundorderModel outboundorderModel = new OutboundorderModel();
            log.info(returnedModel.toString());
            OrderModel orderModel = orderModelMapper.selectOrderByOidM(returnedModel.getOid());
            outboundorderModel.setChanneloid(returnedModel.getChanneloid());//渠道单号
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outboundorderModel.setCreatedtime(date);//创建时间
            outboundorderModel.setModifytime(date);//修改时间
            outboundorderModel.setOid(returnedModel.getOid());
            outboundorderModel.setOrderstatus(orderModel.getOrderstatus());//订单状态
            List<OutboundorderModel> outboundorderModels = outboundorderModelMapper.selectOutboundorders(returnedModel.getOid());
            outboundorderModel.setOutboundid("SO" + returnedModel.getOid() + generateRandomNumber(5));//出库单号
            outboundorderModel.setOutboundstate("待出库");

            outboundorderModel.setSynchrostate(false);//未同步
            outboundorderModel.setReceivername(orderModel.getReceivername());
            outboundorderModel.setReceiveraddress(orderModel.getReceiverprovince() + orderModel.getReceivercity() + orderModel.getReceiverarea() + orderModel.getDetailaddress());
            int i = outboundorderModelMapper.insertSelective(outboundorderModel);
            //将出库单插入数据库

            if (i > 0)
            {
                //拼接传入WMS信息
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setReceiveraddress(outboundorderModel.getReceiveraddress());
                deliveryOrder.setReceiver(outboundorderModel.getReceivername());
                deliveryOrder.setReceivertel(orderModel.getReceivermobel());


                List<RelationrgModel> relationrgModels = relationrgModelMapper.selectAllGoodsByRid(returnedId);
                List<Outboundordergoods> outboundordergoodss = new ArrayList<Outboundordergoods>();
                for (int j = 0; j < relationrgModels.size(); j++)
                {
                    RelationrgModel relationrgModel = relationrgModels.get(j);
                    GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(relationrgModel.getGoodsno());
                    Outboundordergoods outboundordergoods = new Outboundordergoods();
                    outboundordergoods.setName(goodsModel.getGoodsname());
                    outboundordergoods.setNum(relationrgModel.getGoodnum());
                    outboundordergoods.setOutboundnum(relationrgModel.getGoodnum());
                    outboundordergoods.setPrice(goodsModel.getGoodsprice());
                    outboundordergoods.setSku(relationrgModel.getGoodsno());
                    outboundordergoods.setTotalprice(goodsModel.getGoodsprice().multiply(new BigDecimal(relationrgModel.getGoodnum())));//商品总价
                    outboundordergoodss.add(outboundordergoods);
                }
                OutBoundRoot outBoundRoot = new OutBoundRoot();
                outBoundRoot.setChannelorderid(returnedModel.getChanneloid());
                outBoundRoot.setDeliveryOrder(deliveryOrder);
                outBoundRoot.setMessage("");
                outBoundRoot.setOrderid(returnedModel.getOid());
                outBoundRoot.setOutboundordergoods(outboundordergoodss);
                outBoundRoot.setOutboundorderid(outboundorderModel.getOutboundid());

                Boolean synchrostate1 = outboundorderModel.getSynchrostate();
                if (!synchrostate1)
                {
                    String s = sendOutBoundToWMS(outBoundRoot);
                    if (s.equals("100"))
                    {//推送成功
                        log.info("推送出库单" + outboundorderModel.getOutboundid() + "成功");
                        outboundorderModelMapper.updateOutboundSynchrostate(outboundorderModel.getOutboundid());

                    } else if (s.equals("0"))
                    {//链接接口异常
                        log.info("推送出库单" + outboundorderModel.getOutboundid() + "链接接口异常");

                        return "链接接口异常";
                    } else if (s.equals("101"))
                    {//数据格式错误
                        log.info("推送出库单" + outboundorderModel.getOutboundid() + "数据格式错误");

                        return "数据格式错误";
                    } else if (s.equals("102"))
                    {//重复的入库单
                        log.info("推送出库单" + outboundorderModel.getOutboundid() + "重复的出库单");

                        return "重复的出库单";
                    } else
                    {//链接失败
                        return "链接接口异常1";
                    }
                    return "~~";
                } else
                {
                    return "创建出库单失败";
                }
            } else
            {
                return "此订单已同步";
            }
        } else
        {
            return "退货单不是收货成功状态";
        }

    }

    public String checkInBound(Integer id)
    {
        /**
         * @Author: 马潇霄
         * @Description: 审核创建入库单，没有推送WMS，修改人缺省
         * @Date: 11:15 2016/12/15
         * @param returnedIds
         * @Return:
         */

        ReturnedModel returnedModel = returnedModelMapper.selectByPrimaryKey(id);
        String returnedstatus = returnedModel.getReturnedstatus();
        if (returnedstatus.equals("待审核"))
        {

            //待审核订单可以审核
            InboundorderModel inboundorderModel = new InboundorderModel();
            inboundorderModel.setOid(returnedModel.getOid());
            inboundorderModel.setChanneloid(returnedModel.getChanneloid());
            inboundorderModel.setReturnedid(returnedModel.getReturnedid());
            inboundorderModel.setInboundstate("未入库");
            inboundorderModel.setInboundid("SI" + returnedModel.getOid() + generateRandomNumber(5));
            Byte synchrostate = new Byte("0");//同步状态
            inboundorderModel.setSynchrostate(synchrostate);//初始同步状态0为未同步
            OrderModel orderModel = orderModelMapper.selectOrderByOidM(returnedModel.getOid());
            inboundorderModel.setWarehouse(orderModel.getGoodswarehouse());
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            inboundorderModel.setCreatedtime(date);//创建时间
            inboundorderModel.setModifytime(date);//修改时间

            int i = inboundorderModelMapper.countInboundorder(inboundorderModel.getInboundid());
            //查看数据库里是否已经存在
            if (i == 0)
            {
                //数据库不存在
                int i1 = inboundorderModelMapper.insertSelective(inboundorderModel);//将入库单插入数据库，未同步
                if (i1 == 0)
                {
                    return "插入数据库失败";
                } else if (i1 == 1)
                {

                    //以下代码为拼 需要同步给WMS的数据
                    Inbounddeliveryorder inbounddeliveryorder = new Inbounddeliveryorder();
                    inbounddeliveryorder.setReceiver(orderModel.getReceivername());
                    inbounddeliveryorder.setReceivertel(orderModel.getReceivermobel());
                    inbounddeliveryorder.setReceiveraddress(orderModel.getReceiverprovince() + orderModel.getReceivercity() + orderModel.getReceiverarea());
                    List<RelationrgModel> relationrgModels = relationrgModelMapper.selectAllGoodsByRid(returnedModel.getReturnedid());
                    List<Inboundordergoods> inboundordergoodss = new ArrayList<Inboundordergoods>();
                    for (int j = 0; j < relationrgModels.size(); j++)
                    {
                        RelationrgModel relationrgModel = relationrgModels.get(j);
                        GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(relationrgModel.getGoodsno());
                        Inboundordergoods inboundordergoods = new Inboundordergoods();
                        inboundordergoods.setInboundnum(relationrgModel.getGoodnum().toString());//商品入库数量
                        inboundordergoods.setSku(relationrgModel.getGoodsno());//商品编码
                        inboundordergoods.setName(goodsModel.getGoodsname());//商品名称
                        inboundordergoods.setNum(relationrgModel.getGoodnum().toString());//商品总数
                        inboundordergoods.setPrice(goodsModel.getGoodsprice().toString());//商品单价
                        inboundordergoods.setTotalprice(goodsModel.getGoodsprice().multiply(new BigDecimal(relationrgModel.getGoodnum())).toString());//商品总价
                        inboundordergoodss.add(inboundordergoods);
                    }
                    InBoundRoot inBoundRoot = new InBoundRoot();
                    inBoundRoot.setInbounddeliveryorder(inbounddeliveryorder);
                    inBoundRoot.setInboundordergoods(inboundordergoodss);
                    inBoundRoot.setInboundorderid(inboundorderModel.getInboundid());
                    inBoundRoot.setOrderid(inboundorderModel.getOid());
                    inBoundRoot.setReturnorderid(inboundorderModel.getReturnedid());


                    String s="1";//推送给WMS，返回状态码
                    try
                    {
                        s = sendInboundToWMS(inBoundRoot);
                    } catch (Exception e)
                    {
                        log.error("推送入库单给wms失败");
                    }
                    if (s.equals("100"))
                    {//推送成功
                        log.info("推送入库单" + inboundorderModel.getInboundid() + "成功");
                        int i2 = inboundorderModelMapper.updateInboundSynchrostate(inboundorderModel.getInboundid());
                        returnedModelMapper.updateReturnedStatus(returnedModel.getReturnedid(), "审核通过");

                        //更新入库单同步状态为已同步
                        if (i2 > 0)
                        {
                            log.info("更新入库单" + inboundorderModel.getInboundid() + "状态为已同步");
                            return "更新入库单状态为已同步";
                        } else
                        {
                            log.info("更新入库单" + inboundorderModel.getInboundid() + "状态为已同步时发生错误");
                            return "更新入库单状态失败";
                        }

                    } else if (s.equals("0"))
                    {//链接接口异常
                        log.info("推送入库单" + inboundorderModel.getInboundid() + "链接接口异常");

                        return "链接接口异常";
                    } else if (s.equals("101"))
                    {//数据格式错误
                        log.info("推送入库单" + inboundorderModel.getInboundid() + "数据格式错误");

                        return "数据格式错误";
                    } else if (s.equals("102"))
                    {//重复的入库单
                        log.info("推送入库单" + inboundorderModel.getInboundid() + "重复的入库单");

                        return "重复的入库单";
                    } else
                    {//链接失败
                        return "链接接口异常";
                    }
                }

            } else
            {
                returnedModelMapper.updateReturnedStatus(returnedModel.getReturnedstatus(), "审核失败");
                return "入库单已经存在";

            }


        } else
        {
            //退货单状态不是待审核
            return "退货单状态不是待审核";
        }
        return "";
    }

    protected long generateRandomNumber(int n)
    {//生成n位随机数
        if (n < 1)
        {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }

    public String sendInboundToWMS(InBoundRoot inBoundRoot)
    {
        /**
         * @Author: 马潇霄
         * @Description: 推送入库单到WMS
         * @Date: 17:12 2016/12/21
         * @param inBoundRoot
         * @Return: code:"0":连接异常
         * 成功：{“code”:”100”}
         * 失败：{“code”:”101”} 数据格式错误
         *      {“code”:”102”} 重复的入库单
         */
        String dataStr = JSONArray.toJSONString(inBoundRoot);
        String url = "http://114.215.252.146:8080/wms/inboundOrder/receiveOrder";
        log.info(dataStr);
        String code = "";
        try
        {
            HTTPClientDemo httpClientDemo = new HTTPClientDemo(url);
            code = httpClientDemo.postMethod(dataStr);//返回错误码
            CodeRoot coodRoot = JSON.parseObject(code, CodeRoot.class);
            code = coodRoot.getCode();
            log.info(code);

        } catch (Exception e)
        {
            code = "0";//链接接口异常
            log.info("将入库单推送给WMS时链接接口异常" + e.getMessage());
        }
        return code;
    }

    public String sendOutBoundToWMS(OutBoundRoot outBoundRoot)
    {
        /**
         * @Author: 马潇霄
         * @Description: 将出库单推送给WMS
         * @Date: 17:37 2016/12/21
         * @param outBoundRoot
         * @Return: code:"0":连接异常
         * 成功：{“code”:”100”}
         * 失败：{“code”:”101”} 数据格式错误
         *      {“code”:”102”} 重复的入库单
         */
        String dataStr = JSONArray.toJSONString(outBoundRoot);
        log.info(dataStr);
        String url = "http://114.215.252.146:8080/wms/outboundOrder/receiveOrder";
        String code = "";
        try
        {
            HTTPClientDemo httpClientDemo = new HTTPClientDemo(url);
            code = httpClientDemo.postMethod(dataStr);//返回错误码
            CodeRoot coodRoot = JSON.parseObject(code, CodeRoot.class);
            code = coodRoot.getCode();
            log.info(code);
        } catch (Exception e)
        {
            code = "0";//链接接口异常
            log.info("将出库单推送给WMS时链接接口异常" + e.getMessage());
        }
        return code;
    }    //根据退款单号查询该条退货单记录

    public ReturnedModel selectByReturnedId(String returnedId)
    {
        return returnedModelMapper.selectByReturnedId(returnedId);
    }

    public JSONObject getReturnedAndGoodsByid(Integer id)
    {
        ReturnedModel returnedModel = returnedModelMapper.selectByPrimaryKey(id);
        JSONObject json = new JSONObject();
        json.put("returnedModel", returnedModel);
        List<RelationrgModel> relationrgModels = relationrgModelMapper.selectAllGoodsByRid(returnedModel.getReturnedid());

        List<ReturnedSon> returnedSonList = new ArrayList<ReturnedSon>();
        for (int i = 0; i < relationrgModels.size(); i++)
        {
            RelationrgModel relationrgModel = relationrgModels.get(i);
            ReturnedSon returnedSon = new ReturnedSon();
            returnedSon.setGoodnum(relationrgModel.getGoodnum());
            returnedSon.setGoodsno(relationrgModel.getGoodsno());
            GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(returnedSon.getGoodsno());
            returnedSon.setGoodsname(goodsModel.getGoodsname());
            returnedSon.setGoodsprice(goodsModel.getGoodsprice());
            returnedSonList.add(returnedSon);

        }

        json.put("returnedSonList", returnedSonList);
        return json;
    }

    public String getStatus(Integer id)
    {
        ReturnedModel returnedModel = returnedModelMapper.selectByPrimaryKey(id);
        return returnedModel.getReturnedstatus();
    }
}
