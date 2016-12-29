package com.arvato.oms.controller;

import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.GoodsPojo;
import com.arvato.oms.model.InboundorderModel;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.GoodsModelService;
import com.arvato.oms.service.impl.InboundorderServiceImpl;
import com.arvato.oms.service.impl.RelationogServiceImpl;
import org.apache.log4j.Logger;
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

    private Logger log = Logger.getLogger(InboundorderController.class);

    @Resource
    private InboundorderServiceImpl inboserciveimpl;
    @Resource
    private RelationogServiceImpl rogserviceimpl;
    @Resource
    private GoodsModelService godserviceimpl;

    //通过分页查询所有列表 listseach'
     @RequestMapping(value="listseach")
    @ResponseBody
     public String listseach(HttpServletRequest request  )
            throws UnsupportedEncodingException {
         log.info("分页查询所有列表");
         String str = inboserciveimpl.inboundsearchAllByparam (request );
        return  str;
    }

    //子页面显示
    @RequestMapping(value="listinodson")
    @ResponseBody
    public String  listobolson(HttpServletRequest request )
    {
        log.info("子页面显示");
        String str=inboserciveimpl.listSonPage(request);

        return str;
    }
    //详情页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        log.info("详情页面展示");
        String oid=request.getParameter("oid");
        //查询入库单列表
        InboundorderModel iodlist=inboserciveimpl.selectByOid(oid);
        //获取商品编码  查询关系表
        List<RelationogModel> roglist=rogserviceimpl.selectALLByOid(oid);
        //获取商品实体 查询商品表
        List<Object> godslist=new ArrayList<Object>();
        for(int i=0;i<roglist.size();i++){
            GoodsPojo gp=new GoodsPojo();
            //获取商品编号
            String sno= roglist.get(i).getGoodsno();
            //获取商品数量  xiugai---->---------->
            int snum= roglist.get(i).getGoodnum() ;
            GoodsModel gm=godserviceimpl.selectByGoodsNo(sno);
            gp.setGoodNum(snum);
            gp.setGoodsname(gm.getGoodsname());
            gp.setGoodsno(gm.getGoodsno());
            gp.setGoodsprice(gm.getGoodsprice());
            godslist.add(gp);
        }
        model.addAttribute("gods",godslist);
        model.addAttribute("obol",iodlist);

        return "InbounderDetail";
    }

}

