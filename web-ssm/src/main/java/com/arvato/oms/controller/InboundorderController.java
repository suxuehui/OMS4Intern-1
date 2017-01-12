package com.arvato.oms.controller;

import com.arvato.oms.service.impl.InboundorderServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/8.
 */
@Controller
@RequestMapping("inboundorder")
public class InboundorderController {

    private Logger log = Logger.getLogger(InboundorderController.class);

    @Resource
    private InboundorderServiceImpl inboserciveimpl;


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
        inboserciveimpl.inbounderdetail(request,model);
        return "InbounderDetail";
    }

}

