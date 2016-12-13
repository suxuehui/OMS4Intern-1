package com.arvato.oms.controller;

import com.arvato.oms.service.RefoundOrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZHOU169 on 2016/12/12.
 */

@Controller
@RequestMapping("/RefoundOrder/")
public class RefoundOrderController {
    private Logger log = Logger.getLogger(ExceptionController.class);

    @Resource
    private RefoundOrderService refoundOrderService;
    //进入页面
    @RequestMapping(value="indexRefoundOrderList")
    public String indexRefoundOrderList(HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        return "refoundOrder";
    }

    //分页显示出所有异常订单
    @RequestMapping(value = "showRefoundOrderList")
    @ResponseBody
    public String showRefoundOrderList(HttpServletRequest request){
        String str = refoundOrderService.showRefoundOrderList(request);
        return  str;
    }

}
