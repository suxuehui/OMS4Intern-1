package com.arvato.oms.service.impl;

import com.arvato.oms.dao.*;
import com.arvato.oms.model.*;
import com.arvato.oms.service.RefoundOrderService;
import com.arvato.oms.utils.Page;
import com.arvato.oms.utils.ExcToJson;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */
@Service
public class RefoundOrderServiceImpl implements RefoundOrderService{

    @Resource
    private RefoundOrderModelMapper refoundOrderModelMapper;
    @Resource
    private GoodsModelMapper goodsModelMapper;
    @Resource
    private RelationrgModelMapper relationrgModelMapper;
    @Resource
    private RelationogModelMapper relationogModelMapper;
    //分页查询
    public String showRefoundOrderList(HttpServletRequest request)
    {
        //获取当前页数
        String pageNow = request.getParameter("currentpage");
        String txtvalue=request.getParameter("refoundOrderTxtvalue").replaceAll(" ",""); //用户输入的值txtvalue
        int selectValue = Integer.parseInt(request.getParameter("refoundToseachid"))  ;//下拉框的value
        int pagesize=10;
        Page pagelist;
        List<RefoundOrderModel> list;
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(txtvalue==null||"".equals(txtvalue)) {
            if (pageNow != null) {
                totalCount= (int) refoundOrderModelMapper.Count();
                //调用Page工具类传入参数
                pagelist = new Page(totalCount, Integer.parseInt(pageNow), pagesize);
                list  = this.refoundOrderModelMapper.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
            else
            {
                totalCount= (int) refoundOrderModelMapper.Count();
                pagelist= new Page(totalCount, 1,pagesize);
                list = this.refoundOrderModelMapper.selectAll(pagelist.getStartPos(),pagelist.getPageSize());
            }
        }
        else
        {
            //判断下拉框的值确定选择条件，进行数据查询
            if(selectValue==1)
            {
                if (pageNow != null) {
                    totalCount= (int) refoundOrderModelMapper.CountDrawbackId(txtvalue);
                    //调用Page工具类传入参数
                    pagelist = new Page(totalCount, Integer.parseInt(pageNow), pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackId(txtvalue ,pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) refoundOrderModelMapper.CountDrawbackId(txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackId( txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
            }
            else if(selectValue==2)
            {
                String txtvalue2=change2(txtvalue);
                if (pageNow != null) {

                    totalCount= (int) refoundOrderModelMapper.CountdDrawbackStatus( txtvalue2);
                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackStatus( txtvalue2 , pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) refoundOrderModelMapper.CountdDrawbackStatus( txtvalue2);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackStatus(txtvalue2 , pagelist.getStartPos(),pagelist.getPageSize());
                }
            }
            else if(selectValue==3)
            {
                if (pageNow != null) {
                    totalCount= (int) refoundOrderModelMapper.CountReturnedId(txtvalue);
                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.refoundOrderModelMapper.selectAllByReturnedId(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) refoundOrderModelMapper.CountReturnedId(txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.refoundOrderModelMapper.selectAllByReturnedId(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
            }
            else
            {
                pagelist=null;
                list=null;
            }
        }
        //调用对象转json转字符串的工具类
        ExcToJson exctojson = new ExcToJson();
        String jsonstr = exctojson.refobjtojson(pagelist,list);
        return jsonstr ;

    }

    public String change2(String value) {
        if ("待退款".equals(value)) {
            return "1";
        } else if ("退款失败".equals(value)) {
            return "2";
        } else if ("退款成功".equals(value)) {
            return "3";
        } else {
            return value;
        }
    }

    //根据退款号查询该条退款单记录
    public RefoundOrderModel selectByDrawbackId(String drawbackId)
    {
        return this.refoundOrderModelMapper.selectByDrawbackId(drawbackId);
    }


    //子页面显示
    public String listRefoundOrderSon(HttpServletRequest request)
    {
        String drawbackId = request.getParameter("drawbackId");
        //查询退款单列表
        RefoundOrderModel refoundOrderModelList = refoundOrderModelMapper.selectByDrawbackId(drawbackId);
        //获取商品编码 查询关系表
        String returnedId = refoundOrderModelList.getReturnedid();

            String oid = drawbackId.substring(2,17);
            List<RelationogModel> roglist2 = relationogModelMapper.selectMessageByOid(oid);
            //获取商品实体 查询商品表
            List<Object> goodsList2=new ArrayList<Object>();
            for(int i=0;i<roglist2.size();i++){
                //获取商品编号
                String sno= roglist2.get(i).getGoodsno();
                //查询所有商品列
                GoodsModel gm= goodsModelMapper.selectByGoodsNo(sno);
                goodsList2.add(gm);
            }
            //对象转JSON
            JSONArray a1= JSONArray.fromObject(refoundOrderModelList);
            String jsonstr2="{\"refoundOrderModelList\":"+a1.toString(); //异常订单列表
            JSONArray a2 = JSONArray.fromObject(goodsList2); //商品列表
            jsonstr2 +=",\"goods\":"+a2.toString();
            JSONArray a3 = JSONArray.fromObject(roglist2);  //商品与退款单关系列表
            jsonstr2 +=",\"rglist\":"+a3.toString()+"}";
            return jsonstr2;

    }

    public void updataRefoundDrawbackId(String drawbackStatus,String dataNow,String userName,String drawbackId) {
        refoundOrderModelMapper.updataRefoundDrawbackId(drawbackStatus,dataNow,userName,drawbackId);
    }
}
