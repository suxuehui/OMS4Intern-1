package com.arvato.oms.controller;

import com.arvato.oms.model.WarehouseModel;
import com.arvato.oms.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/20.
 */
@Controller
@RequestMapping("warehouse")
public class WarehouseController {
@Resource
private WarehouseService warehouseService ;

    //罗列所有的仓库
    @RequestMapping("listsearch")
    @ResponseBody
    public String listAllwarehouse(HttpServletRequest request) throws UnsupportedEncodingException {
        String str=warehouseService.listAllByparam(request);
        return str;
    }

    //添加仓库
    @RequestMapping("addwarehouse")
    @ResponseBody
    public String addWarehouse(String warehousenum, String warehousename) throws UnsupportedEncodingException {
        int add=warehouseService.addWarehouse(warehousenum,warehousename);
        String str=String.valueOf(add);
        return str ;
    }
     //编辑仓库时将信息展示在弹窗里
     @RequestMapping("listupdateware")
     @ResponseBody
     public WarehouseModel listupdateWarehouse(Integer id) throws UnsupportedEncodingException {
         WarehouseModel warehouse =warehouseService.listupdateWarehouse(id);
         return warehouse;
     }

    //编辑仓库
    @RequestMapping("toupdatewh")
    @ResponseBody
    public String updateWarehouse(WarehouseModel warehouse ) throws UnsupportedEncodingException {
        int update =warehouseService.updateWarehouse(warehouse);
        String str=String.valueOf(update);
        return str;
    }

//删除仓库
     @RequestMapping ("deletewarehouse")
     @ResponseBody
     public String deleteWarehouse (List<Integer> id) throws UnsupportedEncodingException {
         int delete = warehouseService.deleteWarehouseById(id);
         String str = String.valueOf (delete);
         return str;
     }

}
