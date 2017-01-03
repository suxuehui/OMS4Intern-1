package com.arvato.oms.service.impl;


import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.InboundorderModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.InboundorderService;
import com.arvato.oms.utils.ObjectToJsonstr;
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
 * Created by GONG036 on 2016/12/8.
 */
@Service
public class InboundorderServiceImpl implements InboundorderService {
    @Resource
    private InboundorderModelMapper ibodao;

    @Resource
    private GoodsModelMapper gddao;

    @Resource
    private RelationogModelMapper rogdao;
    //分页查询
    public String inboundsearchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException {
        String pageNow = request.getParameter("currentpage").trim ();//获取当前页数pagenow
        String id=request.getParameter("txtvalue").trim (); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        int pagesize=10;//分页的每页显示的数量
        Page pagelist=null;
        List<InboundorderModel> list=null;
        int totalCount=0 ; //获取对象总数量
        String jsonstr=null;//需要返回的json字符串

        if(id==null || id.length()<=0) {
            // 页面显示所有信息
            jsonstr= equalzero(pageNow,totalCount,pagesize,pagelist, list,jsonstr);
        }
        else
        {
            switch (selectvalue){
                case 1:
                    jsonstr= equalone(pageNow,totalCount,id,pagesize,pagelist, list,jsonstr);
                    break;
                case 2:
                    jsonstr= equaltwo(pageNow,totalCount,id,pagesize,pagelist, list,jsonstr);
                    break;
                case 3:
                    jsonstr= equalthree(pageNow,totalCount,id,pagesize,pagelist, list,jsonstr);
                    break;
                case 4:
                    jsonstr=  equalfour(pageNow,totalCount,id,pagesize,pagelist, list,jsonstr);
                    break;
                default:
                    break;
            }
        }
        return jsonstr;
    }
    //进入页面显示全部信息
    public String equalzero(String pageNow,int totalCount,int pagesize,Page pagelist,
                            List<InboundorderModel> list,String jsonstr){
        if (pageNow != null)
        {
            totalCount = (int) ibodao.Count();
            //调用Page工具类传入参数
            pagelist =new Page(totalCount, Integer.parseInt(pageNow), pagesize);

            list = this.ibodao.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
        }
        else
        {
            totalCount = (int) ibodao.Count();
            pagelist =new Page(totalCount, 1,pagesize);
            list = this.ibodao.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.objtojson (pagelist, list,jsonstr);
        return jsonstr;
    }

    //下拉框的值等于1即选中订单号查询
    public String equalone(String pageNow,int totalCount,String id,int pagesize,Page pagelist,
                           List<InboundorderModel> list,String jsonstr){
        if (pageNow != null) {
            totalCount= (int) ibodao.Countoid(id);
            //调用Page工具类传入参数
            pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
            list=this.ibodao.selectAllByOid(id , pagelist.getStartPos(), pagelist.getPageSize());
        } else {
            totalCount= (int) ibodao.Countoid(id);
            pagelist = new Page(totalCount, 1,pagesize);
            list=this.ibodao.selectAllByOid( id , pagelist.getStartPos(), pagelist.getPageSize());
        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.objtojson (pagelist, list,jsonstr);
        return jsonstr;
    }

    //下拉框的值等于2即选中渠道订单号查询
    public String equaltwo(String pageNow,int totalCount,String id,int pagesize,Page pagelist,
                           List<InboundorderModel> list,String jsonstr){
        if (pageNow != null) {

            totalCount= (int) ibodao.Countchid( id);

            pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

            list=this.ibodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
        }
        else {

            totalCount= (int) ibodao.Countoid( id);

            pagelist = new Page(totalCount, 1,pagesize);

            list=this.ibodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.objtojson (pagelist, list,jsonstr);
        return jsonstr;
    }
    //下拉框的值等于3即选中出库单号查询
    public String equalthree(String pageNow,int totalCount,String id,int pagesize,Page pagelist,
                             List<InboundorderModel> list,String jsonstr){
        if (pageNow != null) {

            totalCount= (int) ibodao.Countobid(id);

            pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

            list=this.ibodao.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());

        }

        else {

            totalCount= (int) ibodao.Countobid( id);

            pagelist = new Page(totalCount, 1,pagesize);

            list=this.ibodao.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());

        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.objtojson (pagelist, list,jsonstr);
        return jsonstr;
    }
    //下拉框的值等于4即选中退款号查询
    public String equalfour(String pageNow,int totalCount,String id,int pagesize,Page pagelist,
                            List<InboundorderModel> list,String jsonstr){
        if (pageNow != null) {

            totalCount= (int) ibodao.Countrid(id);

            pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

            list=this.ibodao.selectAllByreturnedId(id , pagelist.getStartPos(), pagelist.getPageSize());
        }

        else {

            totalCount= (int) ibodao.Countrid( id);

            pagelist = new Page(totalCount, 1,pagesize);

            list=this.ibodao.selectAllByreturnedId(id , pagelist.getStartPos(), pagelist.getPageSize());
        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.objtojson (pagelist, list,jsonstr);
        return jsonstr;
    }

    //子页面显示
    public String listSonPage(HttpServletRequest request)
    {
        String oid = request.getParameter("oid");//获取订单oid
        String pagenow=request.getParameter ("currentpage");//获取当前页
        List<RelationogModel> roglist ;
        Page pagelist ;
        int pagesize=5;
        int totalCount ; //获取对象总数量
        //获取商品实体 查询商品表
        List<Object> godslist=new ArrayList<Object>();
        //查询入库单列表
        InboundorderModel iodlist=ibodao.selectByOid(oid);
        totalCount=  this.rogdao.selectCount(oid); //获取总数
        if (pagenow != null)
        {
            //调用Page工具类传入参数Integer pageNo,Integer pageSize,String oId
            pagelist =new  Page(totalCount, Integer.parseInt(pagenow),pagesize);
            roglist=this.rogdao.selectByOid(pagelist.getStartPos(), pagelist.getPageSize(),oid );
        }
        else
        {
            pagelist = new  Page(totalCount, 1,pagesize);
            roglist=this.rogdao.selectByOid (pagelist.getStartPos(), pagelist.getPageSize(),oid);
        }

        for(int i=0;i<roglist.size();i++){
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            //查询所有商品列
            GoodsModel gm= gddao.selectByGoodsNo(sno);
            godslist.add(gm);
        }
        //对象转JSON
        JSONObject a0= JSONObject.fromObject(pagelist);
        String jsonstr="{\"pagelist\":"+a0.toString();//分页
        JSONArray a1= JSONArray.fromObject(iodlist);
        jsonstr +=",\"obolist\":"+a1.toString(); //出库单列表
        JSONArray a2 = JSONArray.fromObject(godslist);   //商品列表
        jsonstr +=",\"gdslist\":"+a2.toString();
        JSONArray a3 = JSONArray.fromObject(roglist );   //商品与订单关系列表
        jsonstr +=",\"rglist\":"+a3.toString()+"}";
        return jsonstr;
    }


    //精确查找by oid
    public  InboundorderModel  selectByOid(String oid) {
        InboundorderModel  list=this.ibodao.selectByOid(oid);
        return list;
    }
    //更新入库单列表
    public int updateByInboundId(String inboundid, String inboundstate,Date modifytime) {
        int s=ibodao.updateByInboundId(inboundid,inboundstate,modifytime);
        return s;
    }
}

