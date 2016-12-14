package com.arvato.oms.service.impl;


import com.arvato.oms.dao.ExceptionModelMapper;
import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.service.ExceptionService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/11.
 */

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Resource
    private ExceptionModelMapper exceptionModelMapper;

    //分页查询
    public String showExceptionOrder(HttpServletRequest request )
    {
        //获取当前页数
        String pageNow = request.getParameter("currentpage");
        System.out.println("pageNow："+pageNow);
        String txtvalue=request.getParameter("txtvalue"); //用户输入的值txtvalue
        System.out.println("txtvalue："+txtvalue);
        int selectValue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        System.out.println("selectValue："+selectValue);
        int pagesize=2;
        Page pagelist = null;
        List<ExceptionModel> list;
//        Map<String,Object> map=new HashMap<String,Object>();
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(txtvalue==null||txtvalue.equals("")) {
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
         System.out.print("jsonstr: "+jsonstr);
        return jsonstr ;
    }

    //根据订单号删除所选异常订单
    public List<ExceptionModel> deleteByOid(String oId) {
        return exceptionModelMapper.deleteByOid(oId);
    }

}
