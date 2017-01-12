package com.arvato.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.service.UserModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController
{

    private Logger log = Logger.getLogger(LoginController.class);

    @Resource
    private UserModelService userModelService;

    @RequestMapping("/checkUser")
    @ResponseBody
    public JSONObject checkUser(HttpServletRequest request, Model model, String uname, String password)
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

        int i = userModelService.login(uname, password);
        log.info("查询用户名密码是否匹配，判断" + uname + "身份是" + i + "密码是" + password);
        HttpSession session = request.getSession();

        session.setAttribute("uname", uname);
        session.setAttribute("urole", userModelService.isAdmin(uname) ? 1 : 2);
        session.setMaxInactiveInterval(30*60);
        JSONObject json = new JSONObject();
        json.put("check", i);
        return json;
    }

    @RequestMapping("/checkUName")
    @ResponseBody
    public int checkUName(String uName)
    {
        return userModelService.checkUname(uName);
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:login";

    }

    @RequestMapping("/checkSession")
    @ResponseBody
    public JSONObject checkSession(HttpSession session)
    {
        JSONObject jsonObject=new JSONObject();
        Integer urole=(Integer)session.getAttribute("urole");
        String uname=(String)session.getAttribute("uname");
        if(urole==null)
        {
            return null;
        }
        jsonObject.put("urole",urole);
        jsonObject.put("uname",uname);
        return jsonObject;
    }

}
