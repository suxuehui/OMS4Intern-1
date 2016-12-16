package com.arvato.oms.controller;

import com.arvato.oms.service.ExceptionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZHOU169 on 2016/12/11.
 */


@Controller
@RequestMapping("/exceptionOrder/")
public class ExceptionController {

    private Logger log = Logger.getLogger(ExceptionController.class);

    @Resource
    private ExceptionService exceptionService;

    //进入页面
    @RequestMapping(value="indexExceptionList")
    public String indexExceptionList(){
        return "exception";
    }

    //分页显示出所有异常订单
    @RequestMapping(value = "showExceptionList")
    @ResponseBody
    public String showExceptionList(HttpServletRequest request,HttpServletResponse response ){

        String str = exceptionService.showExceptionOrder(request );
        return  str;
    }

    //取消异常订单
    @RequestMapping(value = "cancelException")
    public String cancelException(HttpServletRequest request){
        String oid = request.getParameter("oid");
        exceptionService.deleteByOid(oid);
        return "exception";
    }
}
