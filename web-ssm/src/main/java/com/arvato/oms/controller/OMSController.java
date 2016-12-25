package com.arvato.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GONG036 on 2016/12/14.
 */
@Controller
@RequestMapping("omsSystem")
public class OMSController {


    //进入主页面
    @RequestMapping(value = "home")
    public String listseach() {
        return "OMSPage";
    }


}