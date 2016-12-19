package com.arvato.oms.controller;

import com.arvato.oms.service.UserModelService;
import com.arvato.oms.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController
{

    private Logger log = Logger.getLogger(LoginController.class);
   @Resource
   private UserService userService;

    @Resource
    private UserModelService userModelService;

    @RequestMapping("/checkUser")
    public String checkUser(HttpServletRequest request, Model model, HttpSession session, String uname,String password)
    {
        /**
         * @Author: 马潇霄
         * @Description: 判断用户名密码是否匹配，返回0表示不匹配
         *               1表示超级管理员，2表示普通管理员，
         *               3表示既不是超级管理员，又不是普通管理员（基本不会出现，除非数据库填错了）
         * @Date: 16:13 2016/12/7
         * @param request
         * @param model
         * @param session
         * @param uname
         * @param password
         * @Return:
         */
        log.info("查询用户名");
        int i = userModelService.login(uname, password);
        if(i==1||i==2){
            session.setAttribute("uname",uname);
        }
        model.addAttribute("i", i);
        model.addAttribute("session",session.getId());

        return "showUser2";
    }

}
