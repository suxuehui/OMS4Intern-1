package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.*;
import com.arvato.oms.service.OrderService;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Controller
@RequestMapping("/order/")
public class OrderController
{
    private Logger log = Logger.getLogger(OrderController.class);
    private static final String ERRORPAGE="error";
    private static final String ORDERMODEL="orderModel";
    private static final String GOODSPOJOLIST="goodsPojoList";
    private static final String ORDERDETAILS="orderDetails";
    private static final String UNAME="uname";
    private static final String SUCCESS="success";
    private static final String EXCEPTION="exception";

    @Resource
    OrderService orderService;

    //根据订单号查看商品信息
    @RequestMapping("queryGoods")
    public @ResponseBody JSONObject queryGoods(int pageNo,int pageSize,String oId)
    {
        if(pageNo<=0||pageSize<=0||oId==null)
        {
            return null;
        }
        JSONObject jsonObject=orderService.selectByOid(pageNo,pageSize,oId);
        return jsonObject;
    }
    //进入详情页
    @RequestMapping("orderdetail")
    public String orderdetail(String oId,Model model)
    {
        if(oId==null)
        {
            return ERRORPAGE;
        }
        OrderModel orderModel=orderService.selectByOid(oId);
        if(orderModel==null)
        {
            return ERRORPAGE;
        }
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oId);
        model.addAttribute(ORDERMODEL,orderModel);
        model.addAttribute(GOODSPOJOLIST,goodsPojoList);
        return ORDERDETAILS;
    }
    //取消编辑
    @RequestMapping("cancelEdit")
    public String cancelEdit(HttpServletRequest request,Model model)
    {
        String oId=request.getParameter("oId");
        if(oId==null)
        {
            return ERRORPAGE;
        }
        OrderModel orderModel=orderService.selectByOid(oId);
        if(orderModel==null)
        {
            return ERRORPAGE;
        }
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oId);
        model.addAttribute(ORDERMODEL,orderModel);
        model.addAttribute(GOODSPOJOLIST,goodsPojoList);
        return ORDERDETAILS;
    }
    //修改订单信息
    @RequestMapping("modifyInfo")
    public String modifyInfo(HttpServletRequest request, HttpSession session,Model model)
    {
        String oid=request.getParameter("oId");
        if(oid==null)
        {
            return ERRORPAGE;
        }
        OrderModel orderModel=orderService.selectByOid(oid);
        if(orderModel==null)
        {
            return ERRORPAGE;
        }
        orderModel.setReceivername(request.getParameter("receiverName"));
        orderModel.setReceivermobel(request.getParameter("receiverMobel"));
        orderModel.setReceivertelnum(request.getParameter("receiverTelnum"));
        orderModel.setReceiverprovince(request.getParameter("receiverProvince"));
        orderModel.setReceivercity(request.getParameter("receiverCity"));
        orderModel.setReceiverarea(request.getParameter("receiverArea"));
        orderModel.setDetailaddress(request.getParameter("detailAddress"));
        orderModel.setZipcode(request.getParameter("zipCode"));
        orderModel.setModifytime(new Date());
        orderModel.setModifyman((String)session.getAttribute(UNAME));
        orderService.updateByOidSelective(orderModel);
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oid);
        model.addAttribute(ORDERMODEL,orderModel);
        model.addAttribute(GOODSPOJOLIST,goodsPojoList);
        return ORDERDETAILS;
    }
    //条件查询，分页，模糊查询
    @RequestMapping("queryByCondition")
    public @ResponseBody JSONObject queryByOid(int queryMode,int pageNo,int pageSize,String data,Model model)
    {
        if(queryMode<1||queryMode>7||pageNo<=0||pageSize<=0)
        {
            return null;
        }
        JSONObject orderModelList=orderService.selects(queryMode,pageNo,pageSize,"%"+data+"%");
        return orderModelList;
    }

    //检查订单是否可以退换货
    @RequestMapping("checkreturn")
    public @ResponseBody int checkreturn(String oid)
    {
        if(oid==null)
        {
            return 3;//数据有误
        }
        return orderService.checkreturn(oid);
    }

    //退换货
    @RequestMapping("returnGoods")
    public @ResponseBody int returnGoods(String jsonStr)
    {
        return orderService.returnGoods(jsonStr);
    }

    //预检
    @RequestMapping("previewOrder")
    public @ResponseBody JSONObject previewOrder(String[] oIds,HttpSession session)
    {
        if(oIds==null)
        {
            return null;
        }
        int success=0;
        int exception=0;
        for(int i=0;i<oIds.length;i++)
        {
            int j=orderService.previewOrder(oIds[i],null,(String)session.getAttribute(UNAME));
            if(j==1)
            {
                success++;
            }
            else
            {
                exception++;
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(SUCCESS,success);
        jsonObject.put(EXCEPTION,exception);
        return jsonObject;
    }

    //路由
    @RequestMapping("routeOrder")
    public @ResponseBody JSONObject routeOrder(String[] oIds,HttpSession session)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        for(String str:oIds){
            int i=orderService.routeOrder(str,(String)session.getAttribute(UNAME));
            if(i==1)
            {
                success++;
            }
            else
            {
                exception++;
            }
        }
        jsonObject.put(SUCCESS,success);
        jsonObject.put(EXCEPTION,exception);
        return jsonObject;
    }

    //出库
    @RequestMapping("outboundOrder")
    public @ResponseBody JSONObject outboundOrder(String[] oIds,HttpSession session)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        for(String str:oIds)
        {
            int i=orderService.outboundOrder(str,(String)session.getAttribute(UNAME));
            if(i==1)
            {
                success++;
            }
            else
            {
                exception++;
            }
        }
        jsonObject.put(SUCCESS,success);
        jsonObject.put(EXCEPTION,exception);
        return jsonObject;
    }
    //取消订单
    @RequestMapping("cancleOrder")
    public @ResponseBody JSONObject cancleOrder(String[] oIds,HttpSession session)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        for(int i=0;i<oIds.length;i++)
        {
            int j=orderService.cancleOrder(oIds[i],(String)session.getAttribute(UNAME));
            if(j==1)
            {
                success++;
            }
            else
            {
                exception++;
            }
        }
        jsonObject.put(SUCCESS,success);
        jsonObject.put(EXCEPTION,exception);
        return jsonObject;
    }

    //导入excel
    @RequestMapping("importOrder")
    public @ResponseBody int importOrder(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request)
    {
        if(file.isEmpty())
        {
            return 0;//文件为空
        }
        // 得到服务器物理路径名/upload/
        String path = request.getSession().getServletContext()
                .getRealPath(File.separator+"upload"+File.separator);
        SimpleDateFormat stFormat=new SimpleDateFormat("yyyymmddhhMMSS");
        String newfileName=stFormat.format(new Date());
        SimpleDateFormat  stFormat1=new SimpleDateFormat("yyyy");
        String year=stFormat1.format(new Date());
        //path是文件的绝对路径
        path+=File.separator+year;
        //判别文件夹是否存在，若不存在则创建该文件夹
        File file2=new File(path);
        if(!file2.exists())
            file2.mkdirs();
        //得到上传的文件名
        String fileName = file.getOriginalFilename();
        //得到文件后缀名
        newfileName+=fileName.substring(fileName.lastIndexOf("."), fileName.length());
        File targetFile = new File(path, newfileName);
        try {
            //拷贝文件到目录路径
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.info(e);
        }
        return orderService.analysisExcel(path+File.separator+newfileName);
    }
}
