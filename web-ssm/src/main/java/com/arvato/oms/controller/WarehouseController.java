package com.arvato.oms.controller;

import com.arvato.oms.service.impl.WarehouseServiceImpl;
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
private WarehouseServiceImpl warehouseServiceimpl;

    //罗列所有的仓库
    @RequestMapping("listsearch")
    @ResponseBody
    public String listAllwarehouse(HttpServletRequest request) throws UnsupportedEncodingException {
        String str=warehouseServiceimpl.listAllByparam(request);
        return str;
    }

}
