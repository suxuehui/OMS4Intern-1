package com.arvato.oms.service.impl;


import com.arvato.oms.dao.InboundorderDao;
import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.service.InboundorderService;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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

    //分页查询
    public Model searchAllByparam(HttpServletRequest request, Model model) throws UnsupportedEncodingException {

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
        if(id==null) {

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
        model.addAttribute("pagelist",pagelist);
        model.addAttribute("list",list);
        return model;

    }

    public List<InboundorderModel> selectByOid(String oid) {
        List<InboundorderModel> list=this.ibodao.selectByOid(oid);
        return list;
    }

}

