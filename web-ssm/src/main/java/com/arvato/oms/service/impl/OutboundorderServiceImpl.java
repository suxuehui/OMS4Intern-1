package com.arvato.oms.service.impl;

import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.OutboundorderDao;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.OutboundorderModel;

import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.OutboundorderService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GONG036 on 2016/12/6.
 */
 @Service
public class OutboundorderServiceImpl implements OutboundorderService {
    @Resource
    private OutboundorderDao obodao;
    @Resource
    private GoodsModelMapper gddao;
    @Resource
    private RelationogModelMapper rogdao;

//分页查询
    public Model searchAllByparam(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
    //分页查询
    public String searchAllByparam(HttpServletRequest request  ) throws UnsupportedEncodingException {
            String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
            String id=request.getParameter("txtvalue"); //用户输入的值id
            int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
            int pagesize=2;

            Page pagelist  = null;
            List<OutboundorderModel> list;
            Map<String,Object> map=new HashMap<String,Object>();
            //获取对象总数量
            int totalCount ;
        // 页面显示所有信息
   if(id==null) {

     if (pageNow != null)
     {
         totalCount = (int) obodao.Count();
         //调用Page工具类传入参数
         pagelist =new Page(totalCount, Integer.parseInt(pageNow), pagesize);
         list = this.obodao.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
     }
     else
     {
         totalCount = (int) obodao.Count();
         pagelist =new Page(totalCount, 1,pagesize);
         list = this.obodao.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
     }
 }
 else
   {
       //判断下拉框的值确定选择条件，进行数据查询
      if(selectvalue==1)
      {
       if (pageNow != null) {
           totalCount= (int) obodao.Countoid(id);
           //调用Page工具类传入参数
           pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
           list=this.obodao.selectAllByOid(id , pagelist.getStartPos(), pagelist.getPageSize());
       } else {
           totalCount= (int) obodao.Countoid(id);
           pagelist = new Page(totalCount, 1,pagesize);
           list=this.obodao.selectAllByOid( id , pagelist.getStartPos(), pagelist.getPageSize());
       }
      }
      else if(selectvalue==2){
          if (pageNow != null) {

              totalCount= (int) obodao.Countchid( id);

              pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

              list=this.obodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
          } else {

              totalCount= (int) obodao.Countoid( id);

              pagelist = new Page(totalCount, 1,pagesize);

              list=this.obodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
          }
      }
      else if(selectvalue==3){
          if (pageNow != null) {

              totalCount= (int) obodao.Countobid(id);

              pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

              list=this.obodao.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());

          }

          else {

              totalCount= (int) obodao.Countobid( id);

              pagelist = new Page(totalCount, 1,pagesize);

              list=this.obodao.selectAllByoutboundId(id , pagelist.getStartPos(), pagelist.getPageSize());

          }
      }
      else
      {
          pagelist=null;
          list=null;
      }

   }

        model.addAttribute("pagelist",pagelist);
        model.addAttribute("list",list);
        return model;
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;

        }

        //精确查询所有信息by oid
    public List<OutboundorderModel> selectByOid(String oid) {
        List<OutboundorderModel> list=obodao.selectByOid(oid);
    public OutboundorderModel  selectByOid(String oid) {
        OutboundorderModel  list=obodao.selectByOid(oid);
        return list;
    }

    //子页面显示
    public String listSonPage(HttpServletRequest request)
    {
        String oid = request.getParameter("oid");//获取订单oid
        List<OutboundorderModel> list;
        int totalCount ; //获取对象总数量
        //查询出库单列表
        OutboundorderModel obolist=obodao.selectByOid(oid);
        //获取商品编码  查询关系表
        List<RelationogModel> roglist=rogdao.selectALLByOid(oid);
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


}
