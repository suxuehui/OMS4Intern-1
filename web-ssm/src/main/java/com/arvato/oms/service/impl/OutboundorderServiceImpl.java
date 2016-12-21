package com.arvato.oms.service.impl;

import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.dao.OutboundorderModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.OutboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.OutboundorderService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/12.
 */
@Service
public class OutboundorderServiceImpl implements OutboundorderService
{
    @Resource
    OrderModelMapper orderModelMapper;
    @Resource
    OutboundorderModelMapper outboundorderModelMapper;

    @Resource
    private GoodsModelMapper gddao;
    @Resource
    private RelationogModelMapper rogdao;

    public JSONObject outboundOrder(String oId) {
        OutboundorderModel outboundorderModel=new OutboundorderModel();
        OrderModel orderModel=orderModelMapper.selectByOid(oId);
        outboundorderModel.setOid(orderModel.getOid());
        outboundorderModel.setChanneloid(orderModel.getChanneloid());
        outboundorderModel.setOutboundid("SO"+orderModel.getOid());
        outboundorderModel.setSynchrostate(false);
        outboundorderModel.setReceivername(orderModel.getReceivername());
        outboundorderModel.setReceiveraddress(orderModel.getReceiverprovince()+orderModel.getReceivercity()+orderModel.getReceiverarea()+orderModel.getDetailaddress());
        outboundorderModel.setCreatedtime(new Date());
        int i=outboundorderModelMapper.insert(outboundorderModel);

        return null;
    }


    //分页查询
    public String searchAllByparam(HttpServletRequest request) throws UnsupportedEncodingException {
        String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
        String id=request.getParameter("txtvalue").trim(); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        int pagesize=2;
        Page pagelist;
        List<OutboundorderModel> list;
        int totalCount ;//获取对象总数量

        // 页面显示所有信息
        if(id== null || id.length() <= 0) {
            if (pageNow != null)
            {
                totalCount = (int) outboundorderModelMapper.Count();
                //调用Page工具类传入参数
                pagelist =new  Page(totalCount, Integer.parseInt(pageNow), pagesize);
                list = this.outboundorderModelMapper.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
            else
            {
                totalCount = (int) outboundorderModelMapper.Count();
                pagelist =new  Page(totalCount, 1,pagesize);
                list = this.outboundorderModelMapper.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
        }
        else
        {
            //判断下拉框的值确定选择条件，进行数据查询
            if(selectvalue==1)
            {
                if (pageNow != null) {
                    totalCount= (int) outboundorderModelMapper.Countoid(id);
                    //调用Page工具类传入参数
                    pagelist =new  Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.outboundorderModelMapper.selectAllByOid(id , pagelist.getStartPos(), pagelist.getPageSize());
                } else {
                    totalCount= (int) outboundorderModelMapper.Countoid(id);
                    pagelist = new  Page(totalCount, 1,pagesize);
                    list=this.outboundorderModelMapper.selectAllByOid( id , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else if(selectvalue==2){
                if (pageNow != null) {

                    totalCount= (int) outboundorderModelMapper.Countchid( id);

                    pagelist =new  Page(totalCount, Integer.parseInt(pageNow),pagesize);

                    list=this.outboundorderModelMapper.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
                } else {

                    totalCount= (int) outboundorderModelMapper.Countoid( id);

                    pagelist = new  Page(totalCount, 1,pagesize);

                    list=this.outboundorderModelMapper.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else if(selectvalue==3){
                if (pageNow != null) {

                    totalCount= (int) outboundorderModelMapper.Countobid(id);

                    pagelist =new  Page(totalCount, Integer.parseInt(pageNow),pagesize);

                    list=this.outboundorderModelMapper.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());
                }
                else {
                    totalCount= (int) outboundorderModelMapper.Countobid( id);

                    pagelist = new  Page(totalCount, 1,pagesize);

                    list=this.outboundorderModelMapper.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else
            {
                pagelist=null;
                list=null;
            }

        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;

    }

    //子页面显示
    public String listSonPage(HttpServletRequest request)
    {
        String oid = request.getParameter("oid");//获取订单oid
        List<OutboundorderModel> list;
        int totalCount ; //获取对象总数量
        //查询出库单列表
        OutboundorderModel obolist=outboundorderModelMapper.selectByOid(oid);
        //获取商品编码  查询关系表
        List<RelationogModel> roglist=rogdao.selectAllByOid(oid);
        //获取商品实体 查询商品表
        List<Object> godslist=new ArrayList<Object>();

        for(int i=0;i<roglist.size();i++){
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            //查询所有商品列
            GoodsModel gm= gddao.selectByGoodsNo(sno);
            godslist.add(gm);

        }

        //对象转JSON
        JSONArray a1= JSONArray.fromObject(obolist);
        String jsonstr="{\"obolist\":"+a1.toString(); //出库单列表
        JSONArray a2 = JSONArray.fromObject(godslist);   //商品列表
        jsonstr +=",\"goods\":"+a2.toString();
        JSONArray a3 = JSONArray.fromObject(roglist );   //商品与订单关系列表
        jsonstr +=",\"rglist\":"+a3.toString()+"}";
        System.out.println("--------------------"+jsonstr);
        return jsonstr;
    }

    /*精确获取oid*/
    public OutboundorderModel selectByOid(String oid) {
        OutboundorderModel list=outboundorderModelMapper.selectByOid(oid);
        return list;
    }



}
