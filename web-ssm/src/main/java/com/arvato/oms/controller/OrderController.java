package com.arvato.oms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.arvato.oms.model.*;
import com.arvato.oms.service.impl.OrderServiceImpl;
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
    OrderServiceImpl orderService;

    @RequestMapping("OMSPage")
    public String order()
    {
        return "OMSPage";
    }
    @RequestMapping("rorder")
    public String rorder()
    {
        return "rorder";
    }
    @RequestMapping("modify")
    public String modify(String oId,Model model)
    {
        OrderModel orderModel=orderService.selectByOid(oId);
        model.addAttribute("orderModel",orderModel);
        return "modify";
    }
    //分页查询数据库所有订单信息
    @RequestMapping("queryOrders")
    public @ResponseBody JSONObject queryOrders(int pageNo,int pageSize)
    {
        JSONObject jsonObject=orderService.selectAll(pageNo,pageSize);
        return jsonObject;
    }
    //根据订单号查看商品信息
    @RequestMapping("queryGoods")
    public @ResponseBody JSONObject queryGoods(int pageNo,int pageSize,String oId)
    {
        JSONObject jsonObject=orderService.selectByOid(pageNo,pageSize,oId);
        return jsonObject;
    }
    //修改订单信息
    @RequestMapping("modifyInfo")
    public @ResponseBody int modifyInfo(HttpServletRequest request, HttpSession session)
    {
        String oid=request.getParameter("oid");
        OrderModel orderModel=orderService.selectByOid(oid);
        if(orderModel==null)
        {
            return 0;
        }
        orderModel.setGoodswarehouse(request.getParameter("goodsWarehouse"));
        orderModel.setLogisticscompany(request.getParameter("logisticsCompany"));
        orderModel.setLogisticsid(request.getParameter("logisticsId"));
        orderModel.setSendtime(new Date(request.getParameter("sendTime")));
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
        int i=orderService.updateByOidSelective(orderModel);
        return i;
    }
    //条件查询，分页，模糊查询
    @RequestMapping("queryByCondition")
    public @ResponseBody JSONObject queryByOid(int queryMode,int pageNo,int pageSize,String data,Model model)
    {
        JSONObject orderModelList=orderService.selects(queryMode,pageNo,pageSize,"%"+data+"%");
        return orderModelList;
    }
    //退换货
    @RequestMapping("returnGoods")
    public @ResponseBody int returnGoods(String jsonStr)
    {
        System.out.println(jsonStr);
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        ArrayList<GoodsPojo> goodsList=JSON.parseObject(jsonObject.getString("goods"),new TypeReference<ArrayList<GoodsPojo>>(){});
        ReturnedModel returnedModel=new ReturnedModel();
        RelationRgModel[] relationRgModels=new RelationRgModel[goodsList.size()];
        returnedModel.setOid(jsonObject.getString("oid"));
        OrderModel orderModel=orderService.selectByOid(jsonObject.getString("oid"));
        if(orderModel==null)
        {
            return 0;//订单不存在
        }
        if(!orderModel.getOrderstatus().equals("已完成"))
        {
            return 0;//订单未完成
        }
        if(goodsList.size()==0)
        {
            return 0;//没有选择商品
        }
        returnedModel.setChanneloid(jsonObject.getString("channeloid"));
        returnedModel.setReturnedid("RT"+jsonObject.getString("oid"));
        returnedModel.setReturnedorchange(jsonObject.getString("returnedOrChange"));
        double returnedMoney=0.00;
        for(int i=0;i<goodsList.size();i++)
        {
            returnedMoney+=goodsList.get(i).getGoodNum()*(goodsList.get(i).getGoodsprice()).doubleValue();
            relationRgModels[i]=new RelationRgModel();
            relationRgModels[i].setReturnedid("RT"+jsonObject.getString("oid"));
            relationRgModels[i].setGoodsno(goodsList.get(i).getGoodsno());
            relationRgModels[i].setGoodnum(goodsList.get(i).getGoodNum());
        }
        returnedModel.setReturnedmoney(new BigDecimal(returnedMoney));
        returnedModel.setReturnedstatus("待审核");
        Date date=new Date();
        returnedModel.setCreatetime(date);
        int i=orderService.insertSelective(returnedModel);
        if(i==0)
        {
            return 0;
        }
        for(RelationRgModel re:relationRgModels)
        {
            int j=orderService.insertSelective(re);
            if(j==0)
            {
                return 0;
            }
        }
        return 1;
    }

    //预检
    @RequestMapping("previewOrder ")
    public @ResponseBody JSONObject previewOrder(String[] oIds)
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
            int j=orderService.previewOrder(oIds[i],null);
            if(j==0)
            {
                success++;
            }
            else
            {
                exception++;
                ErrorModel errorModel=new ErrorModel();
                if(j==3)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("订单号不存在");
                }
                if(j==2)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("订单状态不符");
                }
                if(j==4)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("异常类型有错");
                }
                if(j==0)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("数据库更新异常");
                }
                errorModelList.add(errorModel);
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("success",success);
        jsonObject.put("exception",exception);
        jsonObject.put("errorList",errorModelList);
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
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("更新数据库异常");
                }
                if(i==2)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("订单不存在");
                }
                if(i==3)
                {
                    errorModel.setoId(oIds[i]);
                    errorModel.setCause("订单状态不符");
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
        // 得到服务器物理路径名/upload/
        String path = request.getSession().getServletContext()
                .getRealPath(File.separator+"upload"+File.separator);
        System.out.println("文件路径" + path);
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
        System.out.println("fileName:" + fileName);
        //得到文件后缀名
        newfileName+=fileName.substring(fileName.lastIndexOf("."), fileName.length());
        System.out.println("newfileName:" + newfileName);
        File targetFile = new File(path, newfileName);
        try {
            //拷贝文件到目录路径
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        analysisExcel(path+File.separator+newfileName);
        return 1;
    }

    public int analysisExcel(String filePath)
    {
        XSSFWorkbook hssfWorkbook=null;
        try {
            InputStream is = new FileInputStream(filePath);
            hssfWorkbook = new XSSFWorkbook(is);
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
                        System.out.println(tradeStr.toString());
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
        return 0;
    }
    //测试接口
    @RequestMapping("interface")
    public @ResponseBody String inter(@RequestBody String str)
    {
        return str;
    }
    @RequestMapping("test")
    public @ResponseBody String test(String str)
    {
        HTTPClientDemo http=new HTTPClientDemo("http://localhost:8080/oms/order/interface");
        String s=http.postMethod(str);
        return s;
    }
}
