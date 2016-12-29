package com.arvato.oms.controller;

import com.arvato.oms.model.WarehouseModel;
import com.arvato.oms.service.WarehouseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/20.
 */
@Controller
@RequestMapping("warehouse")
public class WarehouseController {
    private Logger log = Logger.getLogger(WarehouseController.class);

    @Resource
private WarehouseService warehouseService ;

    //罗列所有的仓库
    @RequestMapping("listsearch")
    @ResponseBody
    public String listAllwarehouse(HttpServletRequest request) throws UnsupportedEncodingException {
        log.info("罗列所有的仓库");
        String str=warehouseService.listAllByparam(request);
        return str;
    }

    //添加仓库
    @RequestMapping("addwarehouse")
    @ResponseBody
    public String addWarehouse(String warehousenum, String warehousename) throws Exception {
        log.info("添加仓库");
        int add=warehouseService.addWarehouse(warehousenum,warehousename);
        String str=String.valueOf(add);
        return str ;
    }
     //编辑仓库时将信息展示在弹窗里
     @RequestMapping("listupdateware")
     @ResponseBody
     public WarehouseModel listupdateWarehouse(Integer id) throws UnsupportedEncodingException {
         log.info("编辑仓库时将信息展示在弹窗里");
         WarehouseModel warehouse =warehouseService.listupdateWarehouse(id);
         return warehouse;
     }

    //编辑仓库
    @RequestMapping("toupdatewh")
    @ResponseBody
    public String updateWarehouse(WarehouseModel warehouse ) throws UnsupportedEncodingException {
        log.info("编辑仓库");
        int update =warehouseService.updateWarehouse(warehouse);
        String str=String.valueOf(update);
        return str;
    }

     //删除仓库
     @RequestMapping ("deleteWarehouse")
     @ResponseBody
     public String deleteWarehouse (String[] id) throws UnsupportedEncodingException {
         log.info("删除仓库");
         int delete = warehouseService.deleteWarehouseById(id);
         String str = String.valueOf (delete);
         return str;
     }

}
