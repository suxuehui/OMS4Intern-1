package com.arvato.oms.service.impl;

import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.dao.OutboundorderModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.OutboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.OutboundorderService;
import com.arvato.oms.utils.ObjectToJsonstr;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    private OutboundorderModelMapper outboundorderModelMapper;

    @Resource
    private GoodsModelMapper gddao;
    @Resource
    private RelationogModelMapper rogdao;

    //分页查询
    public String outboundsearchAllByparam(HttpServletRequest request) throws UnsupportedEncodingException {
        String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
        String id=request.getParameter("txtvalue").trim(); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        int pagesize=2;//每页显示的行数
        Page pagelist=null;
        List<OutboundorderModel> list=null;
        int totalCount=0 ;//获取对象总数量
        String jsonstr=null;//返回json字符串
        // 页面显示所有信息
        if(id== null || id.length() <= 0) {
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
                default:
                    break;
            }
        }
        return jsonstr;
    }
    //选出所有的信息
    public String equalzero(String pageNow, int totalCount, int pagesize, Page pagelist,
                            List<OutboundorderModel> list, String jsonstr){
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
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.outobjtojson (pagelist, list,jsonstr);
        return jsonstr;
    }

    //下拉框的值等于1即选中订单号查询
    public String equalone(String pageNow, int totalCount, String id, int pagesize, Page pagelist,
                           List<OutboundorderModel> list, String jsonstr){
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
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.outobjtojson (pagelist, list,jsonstr);
        return jsonstr;
    }
    //下拉框的值等于2即选中渠道订单号查询
    public String equaltwo(String pageNow, int totalCount, String id, int pagesize, Page pagelist,
                           List<OutboundorderModel> list, String jsonstr){
        if (pageNow != null) {
            totalCount= (int) outboundorderModelMapper.Countchid( id);

            pagelist =new  Page(totalCount, Integer.parseInt(pageNow),pagesize);

            list=this.outboundorderModelMapper.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
        } else {

            totalCount= (int) outboundorderModelMapper.Countoid( id);

            pagelist = new  Page(totalCount, 1,pagesize);

            list=this.outboundorderModelMapper.selectAllBychannelOid( id , pagelist.getStartPos(), pagelist.getPageSize());
        }
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.outobjtojson (pagelist, list,jsonstr);
        return jsonstr;
    }

    //下拉框的值等于3即选中出库单号查询
    public String equalthree(String pageNow, int totalCount, String id, int pagesize, Page pagelist,
                             List<OutboundorderModel> list, String jsonstr){
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
        //调用对象转json转字符串的工具类
        ObjectToJsonstr objtojsonstr=new ObjectToJsonstr ();
        jsonstr= objtojsonstr.outobjtojson (pagelist, list,jsonstr);
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
        return jsonstr;
    }

    /*精确获取oid*/
    public OutboundorderModel selectByOid(String oid) {
        OutboundorderModel list=outboundorderModelMapper.selectByOid(oid);
        return list;
    }

    //向出库表中添加快递公司，快递单号,仓库出库单号的信息,以及修改出库单状态，订单状态
    public void updateOutboundorder(String orderStatus,String outboundState,String warehouseObid,String expressCompany,String expressId,String outboundId ) {
        outboundorderModelMapper.updateOutboundorder(orderStatus,outboundState,warehouseObid,expressCompany,expressId,outboundId);
    }

    //从出库表获取订单号
    public String selectOidByOutboundId(String outboundId) {
        return outboundorderModelMapper.selectOidByOutboundId(outboundId);
    }

}
