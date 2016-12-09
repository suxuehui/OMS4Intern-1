package com.arvato.oms.controller;

import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.service.ExceptionService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.log4j.Logger;
import org.ietf.jgss.Oid;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/6.
 */


@Controller
@RequestMapping("/exceptionOrder/")
public class ExceptionController {

    private Logger log = Logger.getLogger(ExceptionController.class);

    @Resource
    private ExceptionService exceptionService;

    //显示出所有异常订单
    @RequestMapping(value = "showExceptionList")
    public String showExceptionList(HttpServletRequest request, Model model){
        this.exceptionService.showExceptionOrder(request,model);
        return "exception";
    }

   //通过订单号,渠道订单号,异常类型进行查询
   @RequestMapping(value ="queryExceptionOrder")
    public String queryUseOrder(HttpServletRequest request,HttpServletResponse response){
       String text = request.getParameter("text");
       int selected = 0;
       switch(selected){
           case 0:
               List<ExceptionModel> exceptionModel = this.exceptionService.selectByOid(text);
               request.setAttribute("list",exceptionModel);
               break;
           case 1:
               List<ExceptionModel> exceptionModel2 = this.exceptionService.selectByChannelOid(text);
               request.setAttribute("list",exceptionModel2);
               break;
           default:
               List<ExceptionModel> exceptionModel3 = this.exceptionService.selectByExceptionStatus(text);
               request.setAttribute("list",exceptionModel3);
               break;
       }
       return "exception";
   }

    //进入页面
//    @RequestMapping(value="listindex")
//    public String listseach(){
//        return "OutboundOrder";
//    }


    //通过分页查询所有列表 listseach'
//    @RequestMapping(value="listseach")
//    @ResponseBody
//    public Model listseach(HttpServletRequest request , Model model)
//            throws UnsupportedEncodingException{
//        model = exceptionService.searchAllByparam(request,model);
//        return  model;
//    }


}
