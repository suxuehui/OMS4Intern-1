package com.arvato.oms.controller;

import com.arvato.oms.service.WarehouseService;
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
        System.out.println("添加仓库++++++"+str);
        return str ;
    }


}
