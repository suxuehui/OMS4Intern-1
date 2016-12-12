package com.arvato.oms.controller;

import com.arvato.oms.model.goodsModel;
import com.arvato.oms.model.inboundorderModel;
import com.arvato.oms.model.relationogModel;
import com.arvato.oms.model.GoodsModel ;
import com.arvato.oms.model.InboundorderModel ;
import com.arvato.oms.model.RelationogModel ;
import com.arvato.oms.service.impl.GoodsServiceImpl;
import com.arvato.oms.service.impl.InboundorderServiceImpl;
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
 * Created by GONG036 on 2016/12/8.
 */
@Controller
@RequestMapping("inboundorder")
public class InboundorderController {

    @Resource
    private InboundorderServiceImpl inboserciveimpl;
    @Resource
    private RelationOGServiceImpl rogserviceimpl;
    @Resource
    private GoodsServiceImpl godserviceimpl;
    //进入页面
    @RequestMapping(value="listindex")
    public String listseach(){
        return "InboundOrder";
    }

    //通过分页查询所有列表 listseach'
    @RequestMapping(value="listseach")
    @ResponseBody
    public Model listseach(HttpServletRequest request , Model model)
    public String listseach(HttpServletRequest request  )
            throws UnsupportedEncodingException {
        model = inboserciveimpl.searchAllByparam(request,model);
        return  model;
        String str = inboserciveimpl.searchAllByparam(request );
        return  str;
    }

    //子页面显示
    @RequestMapping(value="listinodson")
    @ResponseBody
    public String  listobolson(HttpServletRequest request )
    {
        String str=inboserciveimpl.listSonPage(request);
        System.out.println("子页面----"+str);
        return str;
    }


    //详情页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        String oid=request.getParameter("oid");
        //查询入库单列表
       List<inboundorderModel> obolist=inboserciveimpl.selectByOid(oid);
        InboundorderModel  obolist=inboserciveimpl.selectByOid(oid);
        //获取商品编码  查询关系表
        List<relationogModel> roglist=rogserviceimpl.selectByOid(oid);
        List<RelationogModel> roglist=rogserviceimpl.selectALLByOid(oid);
        //获取商品实体 查询商品表
        List<Object> godslist=new ArrayList<Object>();
         for(int i=0;i<roglist.size();i++){
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            goodsModel gm=godserviceimpl.selectByGoodsNo(sno);
            GoodsModel gm=godserviceimpl.selectByGoodsNo(sno);
            godslist.add(gm);
        }
        model.addAttribute("gods",godslist);
        model.addAttribute("obol",obolist);
        model.addAttribute("rog",roglist);
        model.addAttribute("oid",oid);

        return "InbounderDetail";
    }



}

