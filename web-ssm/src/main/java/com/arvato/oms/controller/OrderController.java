package com.arvato.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.arvato.oms.model.ErrorModel;
import com.arvato.oms.model.*;
import com.arvato.oms.service.OrderService;
import com.arvato.oms.utils.HTTPClientDemo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHAN545 on 2016/12/6.
 */
@Controller
@RequestMapping("/order/")
public class OrderController
{
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
            return "error";
        }
        OrderModel orderModel=orderService.selectByOid(oId);
        if(orderModel==null)
        {
            return "error";
        }
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oId);
        model.addAttribute("orderModel",orderModel);
        model.addAttribute("goodsPojoList",goodsPojoList);
        return "orderDetails";
    }
    //取消编辑
    @RequestMapping("cancelEdit")
    public String cancelEdit(HttpServletRequest request,Model model)
    {
        String oId=request.getParameter("oId");
        if(oId==null)
        {
            return "error";
        }
        OrderModel orderModel=orderService.selectByOid(oId);
        if(orderModel==null)
        {
            return "error";
        }
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oId);
        model.addAttribute("orderModel",orderModel);
        model.addAttribute("goodsPojoList",goodsPojoList);
        return "orderDetails";
    }
    //修改订单信息
    @RequestMapping("modifyInfo")
    public String modifyInfo(HttpServletRequest request, HttpSession session,Model model)
    {
        String oid=request.getParameter("oId");
        if(oid==null)
        {
            return "error";
        }
        OrderModel orderModel=orderService.selectByOid(oid);
        if(orderModel==null)
        {
            return "error";
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
        orderModel.setModifyman((String)session.getAttribute("uname"));
        orderService.updateByOidSelective(orderModel);
        List<GoodsPojo> goodsPojoList=orderService.selectGoodsByOid(oid);
        model.addAttribute("orderModel",orderModel);
        model.addAttribute("goodsPojoList",goodsPojoList);
        return "orderDetails";
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
        int success=0;
        int exception=0;
        if(oIds==null)
        {
            return null;
        }
        List<ErrorModel> errorModelList=new ArrayList<ErrorModel>();
        for(int i=0;i<oIds.length;i++)
        {
            int j=orderService.previewOrder(oIds[i],null,(String)session.getAttribute("uname"));
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
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        return jsonObject;
    }

    //路由
    @RequestMapping("routeOrder")
    public @ResponseBody JSONObject routeOrder(String[] oIds)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        List<ErrorModel> errorModelList=new ArrayList<ErrorModel>();
        for(String str:oIds){
            int i=orderService.routeOrder(str);
            if(i==1)
            {
                success++;
            }
            else
            {
                exception++;
                ErrorModel errorModel=new ErrorModel();
                if(i==2)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("订单状态不符");
                }
                if(i==3)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("订单不存在");
                }
                if(i==0)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("数据库操作异常");
                }
                errorModelList.add(errorModel);
            }
        }
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        jsonObject.put("errorList",errorModelList);
        return jsonObject;
    }

    //出库
    @RequestMapping("outboundOrder")
    public @ResponseBody JSONObject outboundOrder(String[] oIds)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        List<ErrorModel> errorModelList=new ArrayList<ErrorModel>();
        for(String str:oIds)
        {
            int i=orderService.outboundOrder(str);
            if(i==1)
            {
                success++;
            }
            else
            {
                exception++;
                ErrorModel errorModel=new ErrorModel();
                if(i==0)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("更新数据库异常");
                }
                if(i==3)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("出库异常");
                }
                if(i==2)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("订单不存在");
                }
                if(i==4)
                {
                    errorModel.setoId(str);
                    errorModel.setCause("订单状态不符");
                }
            }
        }
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        jsonObject.put("errorList",errorModelList);
        return jsonObject;
    }
    //取消订单
    @RequestMapping("cancleOrder")
    public @ResponseBody JSONObject cancleOrder(String[] oIds)
    {
        int success=0;
        int exception=0;
        JSONObject jsonObject=new JSONObject();
        List<ErrorModel> errorModelList=new ArrayList<ErrorModel>();
        for(int i=0;i<oIds.length;i++)
        {
            int j=orderService.cancleOrder(oIds[i]);
            if(j==1)
            {
                success++;
            }
            else
            {
                exception++;
                ErrorModel errorModel=new ErrorModel();
                if(i==0)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("更新数据库异常");
                }
                if(i==2)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("订单不存在");
                }
            }
        }
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        jsonObject.put("errorList",errorModelList);
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
            e.printStackTrace();
        }
        return analysisExcel(path+File.separator+newfileName);
    }

    public int analysisExcel(String filePath)
    {
        XSSFWorkbook hssfWorkbook=null;
        try {
            InputStream is = new FileInputStream(filePath);
            try {
                hssfWorkbook = new XSSFWorkbook(is);
            }catch(Exception e)
            {
                e.printStackTrace();
                return 2;
            }
            finally {
                is.close();
            }
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        XSSFCell tradeStr = hssfRow.getCell(0);
                        orderService.importOrder(tradeStr.toString());
                    }
                }
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return 1;
    }
}
