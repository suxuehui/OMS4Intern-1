package com.arvato.oms.service.impl;

import com.arvato.oms.dao.RefoundOrderModelMapper;
import com.arvato.oms.model.RefoundOrderModel;
import com.arvato.oms.service.RefoundOrderService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/12.
 */
@Service
public class RefoundOrderServiceImpl implements RefoundOrderService{

    @Resource
    private RefoundOrderModelMapper refoundOrderModelMapper;

    //分页查询
    public String showRefoundOrderList(HttpServletRequest request)
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
        List<RefoundOrderModel> list;
//        Map<String,Object> map=new HashMap<String,Object>();
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(txtvalue==null||txtvalue.equals("")) {
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
                if (pageNow != null) {
                    totalCount= (int) refoundOrderModelMapper.CountdDrawbackStatus( txtvalue);
                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackStatus( txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
                }
                else
                {
                    totalCount= (int) refoundOrderModelMapper.CountdDrawbackStatus( txtvalue);
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.refoundOrderModelMapper.selectAllByDrawbackStatus(txtvalue , pagelist.getStartPos(),pagelist.getPageSize());
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
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        System.out.print("jsonstr: "+jsonstr);
        return jsonstr ;
    }
}
