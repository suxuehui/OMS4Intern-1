package com.arvato.oms.service.impl;



import com.arvato.oms.dao.ExceptionModelMapper;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.ExceptionService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/11.
 */

@Service
public class ExceptionServiceImpl implements ExceptionService {


    @Resource
    private ExceptionModelMapper exceptionModelMapper;
    @Resource
    private GoodsModelMapper goodsModelMapper;
    @Resource
    private RelationogModelMapper relationogModelMapper;
    //分页查询
    public String showExceptionOrder(HttpServletRequest request )
    {
        //获取当前页数
        String pageNow = request.getParameter("currentpage");
        String txtvalue=request.getParameter("txtvalue"); //用户输入的值txtvalue
        int selectValue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        int pagesize=10;
        Page pagelist;
        List<ExceptionModel> list;
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(txtvalue==null||"".equals(txtvalue)) {
            if (pageNow != null) {
                totalCount= (int) exceptionModelMapper.Count();
                //调用Page工具类传入参数
                pagelist = new Page(totalCount, Integer.parseInt(pageNow), pagesize);
                list  = this.exceptionModelMapper.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
            else
            {
                totalCount= (int) exceptionModelMapper.Count();
                pagelist= new Page(totalCount, 1,pagesize);
                list = this.exceptionModelMapper.selectAll(pagelist.getStartPos(),pagelist.getPageSize());
            }
        }
        else
        {
            //判断下拉框的值确定选择条件，进行数据查询
            if(selectValue==1)
            {
                if (pageNow != null) {
                    totalCount= (int) exceptionModelMapper.Countoid(txtvalue);
                    //调用Page工具类传入参数
                    pagelist = new Page(totalCount, Integer.parseInt(pageNow), pagesize);
                    list=this.exceptionModelMapper.selectAllByOid(txtvalue ,pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) exceptionModelMapper.Countoid(txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.exceptionModelMapper.selectAllByOid( txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
            }
            else if(selectValue==2)
            {
                if (pageNow != null) {
                    totalCount= (int) exceptionModelMapper.Countchid( txtvalue);
                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.exceptionModelMapper.selectAllBychannelOid( txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) exceptionModelMapper.Countchid( txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.exceptionModelMapper.selectAllBychannelOid(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
            }
            else if(selectValue==3)
            {
                if (pageNow != null) {
                    totalCount= (int) exceptionModelMapper.Counttype(txtvalue);
                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.exceptionModelMapper.selectAllByexceptionType(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) exceptionModelMapper.Counttype(txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.exceptionModelMapper.selectAllByexceptionType(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
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
        return jsonstr ;
    }

    //根据订单号删除所选异常订单
    public List<ExceptionModel> deleteByOid(String oId)
    {
        return exceptionModelMapper.deleteByOid(oId);
    }

    //根据订单号查询该条异常订单记录
    public ExceptionModel selectByOid(String oId)
    {
        return this.exceptionModelMapper.selectByExceptionOid(oId);
    }

    //根据订单号查询该条订单的异常类型
    public String selectTypeByOid(String oId) {
        return exceptionModelMapper.selectTypeByOid(oId);
    }

    //子页面显示
    public String listExceptionSon(HttpServletRequest request)
    {
        String oid = request.getParameter("oid3");//获取订单oid
        //查询出库单列表
        ExceptionModel exceptionList = exceptionModelMapper.selectByExceptionOid(oid);
        //获取商品编码  查询关系表
        List<RelationogModel> roglist = relationogModelMapper.selectMessageByOid(oid);
        //获取商品实体 查询商品表
        List<Object> goodsList=new ArrayList<Object>();

        for(int i=0;i<roglist.size();i++){
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            //查询所有商品列
            GoodsModel gm= goodsModelMapper.selectByGoodsNo(sno);
            goodsList.add(gm);
        }

        //对象转JSON
        JSONArray a1= JSONArray.fromObject(exceptionList);
        String jsonstr="{\"exceptionList\":"+a1.toString(); //异常订单列表
        JSONArray a2 = JSONArray.fromObject(goodsList); //商品列表
        jsonstr +=",\"goods\":"+a2.toString();
        JSONArray a3 = JSONArray.fromObject(roglist);  //商品与订单关系列表
        jsonstr +=",\"rglist\":"+a3.toString()+"}";
        return jsonstr;
    }

    public int insertSelective(ExceptionModel exceptionModel) {
        return exceptionModelMapper.insertSelective(exceptionModel);
    }
}
