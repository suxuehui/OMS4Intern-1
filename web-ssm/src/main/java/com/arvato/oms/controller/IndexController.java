package com.arvato.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by 马潇霄 on 2016/12/29.
 */

@Controller
@RequestMapping("/oms")
public class IndexController
{
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session)
    {
        ModelAndView mov = new ModelAndView("OMSPage");
        Object urole = session.getAttribute("urole");
        Object uname = session.getAttribute("uname");
        mov.addObject("urole",urole);
        mov.addObject("uname",uname);
        return mov;
    }


}
