package com.arvato.oms.service.impl;


import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.InboundorderDao;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.OutboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.InboundorderService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GONG036 on 2016/12/8.
 */
@Service
public class InboundorderServiceImpl implements InboundorderService {
    @Resource
    private InboundorderDao ibodao;

    @Resource
    private GoodsModelMapper gddao;
    @Resource
    private RelationogModelMapper rogdao;
    //分页查询
      public String searchAllByparam(HttpServletRequest request ) throws UnsupportedEncodingException {

        String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
        String id=request.getParameter("txtvalue"); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        int pagesize=2;
        Page pagelist  = null;
        List<InboundorderModel> list;
        Map<String,Object> map=new HashMap<String,Object>();
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(id==null || id.length()<=0) {

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
        }
        else
        {
            //判断下拉框的值确定选择条件，进行数据查询
            if(selectvalue==1)
            {
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
            }
            else if(selectvalue==2){
                if (pageNow != null) {

                    totalCount= (int) ibodao.Countchid( id);

                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

                    list=this.ibodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
                } else {

                    totalCount= (int) ibodao.Countoid( id);

                    pagelist = new Page(totalCount, 1,pagesize);

                    list=this.ibodao.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else if(selectvalue==3){
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
            }
            else if(selectvalue==4){
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
        InboundorderModel iodlist=ibodao.selectByOid(oid);
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
        JSONArray a1= JSONArray.fromObject(iodlist);
        String jsonstr="{\"obolist\":"+a1.toString(); //出库单列表
        JSONArray a2 = JSONArray.fromObject(godslist);   //商品列表
        jsonstr +=",\"goods\":"+a2.toString();
        JSONArray a3 = JSONArray.fromObject(roglist );   //商品与订单关系列表
        jsonstr +=",\"rglist\":"+a3.toString()+"}";
        System.out.println("--------------------"+jsonstr);
        return jsonstr;
    }


    //精确查找by oid
    public  InboundorderModel  selectByOid(String oid) {
        InboundorderModel  list=this.ibodao.selectByOid(oid);
        return list;
    }

}

