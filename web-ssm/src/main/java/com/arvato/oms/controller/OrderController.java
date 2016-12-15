package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.service.GoodsService;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.service.ReturnedModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Controller
@RequestMapping("/order/")
public class OrderController
{
    @Resource
    OrderService orderService;
    @Resource
    GoodsService goodsService;
    @Resource
    ReturnedModelService returnedModelService;

    @RequestMapping("order")
    public String order()
    {
        return "order";
    }
    @RequestMapping("rorder")
    public String rorder()
    {
        return "rorder";
    }
    @RequestMapping("modify")
    public String modify(String oId,Model model)
    {
        OrderModel orderModel=orderService.selectByOid(oId);
        model.addAttribute("orderModel",orderModel);
        return "modify";
    }
    //分页查询数据库所有订单信息
    @RequestMapping("queryOrders")
    public @ResponseBody JSONObject queryOrders(int pageNo,int pageSize)
    {
        JSONObject jsonObject=orderService.selectAll(pageNo,pageSize);
        return jsonObject;
    }
    //根据订单号查看商品信息
    @RequestMapping("queryGoods")
    public @ResponseBody JSONObject queryGoods(int pageNo,int pageSize,String oId)
    {
        JSONObject jsonObject=goodsService.selectByOid(pageNo,pageSize,oId);
        return jsonObject;
    }
    //修改订单信息
    @RequestMapping("modifyInfo")
    public @ResponseBody int modifyInfo(HttpServletRequest request, HttpSession session)
    {
        String oid=request.getParameter("oid");
        OrderModel orderModel=orderService.selectByOid(oid);
        orderModel.setGoodswarehouse(request.getParameter("goodsWarehouse"));
        orderModel.setLogisticscompany(request.getParameter("logisticsCompany"));
        orderModel.setLogisticsid(request.getParameter("logisticsId"));
        orderModel.setSendtime(new Date(request.getParameter("sendTime")));
        orderModel.setReceivername(request.getParameter("receiverName"));
        orderModel.setReceivermobel(request.getParameter("receiverMobel"));
        orderModel.setReceivertelnum(request.getParameter("receiverTelnum"));
        orderModel.setReceiverprovince(request.getParameter("receiverProvince"));
        orderModel.setReceivercity(request.getParameter("receiverCity"));
        orderModel.setReceiverarea(request.getParameter("receiverArea"));
        orderModel.setDetailaddress(request.getParameter("detailAddress"));
        orderModel.setZipcode(request.getParameter("zipCode"));
        orderModel.setModifytime(new Date());
        orderModel.setModifyman((String)session.getAttribute("uname"));
        int i=orderService.updateByOidSelective(orderModel);
        return i;
    }
    //条件查询，分页，模糊查询
    @RequestMapping("queryByCondition")
    public @ResponseBody JSONObject queryByOid(int queryMode,int pageNo,int pageSize,String data,Model model)
    {
        JSONObject orderModelList=orderService.selects(queryMode,pageNo,pageSize,"%"+data+"%");
        return orderModelList;
    }
    //退换货
  /*  @RequestMapping("returnGoods")
    public @ResponseBody int returnGoods(String jsonStr)
    {
        System.out.println(jsonStr);
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        ArrayList<GoodsPojo> goodsList=JSON.parseObject(jsonObject.getString("goods"),new TypeReference<ArrayList<GoodsPojo>>(){});
        ReturnedModel returnedModel=new ReturnedModel();
        RelationRgModel[] relationRgModel=new RelationRgModel[goodsList.size()];
        returnedModel.setOid(jsonObject.getString("oid"));
        OrderModel orderModel=orderService.selectByOid(jsonObject.getString("oid"));
        if(!orderModel.getOrderstatus().equals("已完成"))
        {
            return 0;
        }
        returnedModel.setChanneloid(jsonObject.getString("channeloid"));
        returnedModel.setReturnedid("RT"+jsonObject.getString("oid"));
        returnedModel.setReturnedorchange(jsonObject.getString("returnedOrChange"));
        double returnedMoney=0.00;
        for(int i=0;i<goodsList.size();i++)
        {
            returnedMoney+=goodsList.get(i).getGoodNum()*(goodsList.get(i).getGoodsprice()).doubleValue();
            relationRgModel[i]=new RelationRgModel();
             relationRgModel[i].setReturnedid("RT"+jsonObject.getString("oid"));
            relationRgModel[i].setGoodsno(goodsList.get(i).getGoodsno());
            relationRgModel[i].setGoodnum(goodsList.get(i).getGoodNum());
        }
        returnedModel.setReturnedmoney(new BigDecimal(returnedMoney));
        returnedModel.setReturnedstatus("待审核");
        Date date=new Date();
        returnedModel.setCreatetime(date);
        int i=returnedModelService.insertSelective(returnedModel);
        if(i==0)
        {
            return 0;
        }
        for(RelationRgModel re:relationRgModel)
        {
            int j=returnedModelService.insertSelective(re);
            if(j==0)
            {
                return 0;
            }
        }
        return 1;
    }
*/
    //路由
    @RequestMapping("routeOrder")
    public @ResponseBody int routeOrder(String[] oIds)
    {
        for(String str:oIds){
            System.out.println(str);
        }
        int count=0;
        for(int i=0;i<oIds.length;i++)
        {
            OrderModel orderModel=orderService.selectByOid(oIds[i]);
            orderModel.setGoodswarehouse("南京仓");
            int j=0;
            j=orderService.updateByOidSelective(orderModel);
            count++;
        }
        return count;
    }

    //预检
    @RequestMapping("previewOrder ")
    public @ResponseBody JSONObject previewOrder(String[] oIds)
    {
        int success=0;
        int exception=0;
        for(int i=0;i<oIds.length;i++)
        {
            int j=orderService.previewOrder(oIds[i],null);
            if(j==0)
            {
                success++;
            }
            else
            {
                exception++;
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        return jsonObject;
    }

    //出库
    @RequestMapping("outboundOrder")
    public @ResponseBody int outboundOrder(String[] oIds)
    {
        for(String str:oIds)
        {
            
        }
        return 0;
    }
    //取消订单
    @RequestMapping("cancleOrder")
    public @ResponseBody int cancleOrder(String[] oIds){
        return 0;
    }
}
