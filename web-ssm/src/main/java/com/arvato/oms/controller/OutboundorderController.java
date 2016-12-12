package com.arvato.oms.controller;

import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.OutboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.impl.GoodsServiceImpl;
import com.arvato.oms.service.impl.OutboundorderServiceImpl;
import com.arvato.oms.service.impl.RelationOGServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/6.
 */
@Controller
@RequestMapping("outboundorder")
public class OutboundorderController {
    @Resource
    private OutboundorderServiceImpl oboserciveimpl;
    @Resource
    private RelationOGServiceImpl rogserviceimpl;
    @Resource
    private GoodsServiceImpl godserviceimpl;

    //进入页面
    @RequestMapping(value="listindex")
    public String listseach(){
        return "OutboundOrder";
    }


    //通过分页查询所有列表 listseach'
    @RequestMapping(value="listseach")
    @ResponseBody
    public Model listseach(HttpServletRequest request , Model model)
    public String listseach(HttpServletRequest request )
            throws UnsupportedEncodingException{
            model = oboserciveimpl.searchAllByparam(request,model);
            return  model;
            String str = oboserciveimpl.searchAllByparam(request );
            return  str;
    }



    //子页面显示
    @RequestMapping(value="listinodson")
    @ResponseBody
    public String  listobolson(HttpServletRequest request )
    {
        String str=oboserciveimpl.listSonPage(request);
        System.out.println("子页面----"+str);
        return str;
    }

    //详情页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        String oid=request.getParameter("oid");
        //查询出库单列表
        List<OutboundorderModel> obolist=oboserciveimpl.selectByOid(oid);
        OutboundorderModel  obolist=oboserciveimpl.selectByOid(oid);
       //获取商品编码  查询关系表
        List<RelationogModel> roglist=rogserviceimpl.selectALLByOid(oid);
        //获取商品实体 查询商品表
      List<Object> godslist=new ArrayList<Object>();
        for(int i=0;i<roglist.size();i++){
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            //获取商品数量
            int snum= roglist.get(i).getGoodnum();
            GoodsModel gm=godserviceimpl.selectByGoodsNo(sno);
            godslist.add(gm);
            System.out.println("11==========="+sno+"-----"+gm);
        }
        model.addAttribute("gods",godslist);
        model.addAttribute("obol",obolist);
        model.addAttribute("rog",roglist);
        model.addAttribute("oid",oid);
        System.out.println("22==========="+model);
        return "OutbounderDetail";
    }

}
