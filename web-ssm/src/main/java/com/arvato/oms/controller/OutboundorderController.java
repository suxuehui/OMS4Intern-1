package com.arvato.oms.controller;

import com.arvato.oms.service.OutboundorderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/6.
 */
@Controller
@RequestMapping("outboundorder")
public class OutboundorderController {
    private Logger log = Logger.getLogger(OutboundorderController.class);

    @Resource
    private OutboundorderService oboserciveimpl;

    //通过分页查询所有列表 listseach'
    @RequestMapping(value="listseach")
    @ResponseBody
     public String listseach(HttpServletRequest request ) throws UnsupportedEncodingException{
        log.info("出库分页查询所有列表");
        String str = oboserciveimpl.outboundsearchAllByparam(request);
        return  str;
    }

    //子页面显示
    @RequestMapping(value="listobolson")
    @ResponseBody
    public String  listobolson(HttpServletRequest request )
    {
        log.info("出库子页面显示");
        String str=oboserciveimpl.listSonPage(request);
        return str;
    }
    //详情页面展示
    @RequestMapping(value="details")
    public String  details(HttpServletRequest request,Model model){
        log.info("出库详情页面展示");
        oboserciveimpl.outboundorderdetail (request,model);
        return "OutbounderDetail";
    }

}
