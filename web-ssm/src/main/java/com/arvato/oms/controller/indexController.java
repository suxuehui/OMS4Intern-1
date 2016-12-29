package com.arvato.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 马潇霄 on 2016/12/29.
 */

@Controller
@RequestMapping("/oms")
public class indexController
{
    @RequestMapping("/index")
    public ModelAndView index(int urole)
    {
        ModelAndView mov = new ModelAndView("OMSPage");
        mov.addObject("urole",urole);
        return mov;
    }
}
