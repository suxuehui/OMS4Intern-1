package com.arvato.oms.service.impl;

import com.arvato.oms.dao.OutboundorderDao;
import com.arvato.oms.model.OutboundorderModel;

import com.arvato.oms.service.OutboundorderService;
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
 * Created by GONG036 on 2016/12/6.
 */
 @Service
public class OutboundorderServiceImpl implements OutboundorderService {
    @Resource
    private OutboundorderDao obodao;

//分页查询
    public Model searchAllByparam(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
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

        }

        //精确查询所有信息by oid
    public List<OutboundorderModel> selectByOid(String oid) {
        List<OutboundorderModel> list=obodao.selectByOid(oid);
        return list;
    }

}
