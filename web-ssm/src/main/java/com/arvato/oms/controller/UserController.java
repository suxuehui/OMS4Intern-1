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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */

@Controller
@RequestMapping("/user")
public class UserController
{

    private Logger log = Logger.getLogger(UserController.class);

    public  static final  String UROLE = "urole";
    @Resource
    private UserModelService userModelService;

    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(HttpServletRequest request, Model model,String userName,String password)
    {
        /**
         * @Author: 马潇霄
         * @Description: 添加用户，返回1为添加成功，0为添加失败
         * @Date: 16:14 2016/12/7
         * @param request
         * @param model
         * @Return:
         */
        log.info("添加用户");
        int checkCount = userModelService.checkUname(userName);//用户名是否存在状态码，0为不存在，1为存在，
        // 2为多条同名用户（异常情况）
        HttpSession session = request.getSession();
        int urole = (Integer) session.getAttribute(UROLE);
        if (urole==2){
            return -5;
        }else {
            if (checkCount == 0)
            {
                return userModelService.addUser(userName, password);
            } else
            {
                return 0;
            }
        }

    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public int updateUser(HttpServletRequest request, Model model,int uid,String userName,String password)
    {
        /**
         * @Author: 马潇霄
         * @Description: 修改用户信息
         * @Date: 16:15 2016/12/7
         * @param request
         * @param model
         * @Return:
         */
        log.info("修改用户");
        int checkCount = userModelService.checkUnameEXself(userName,uid);
        HttpSession session = request.getSession();
        int urole = (Integer) session.getAttribute(UROLE);
        if (urole==2){
            return -5;
        }else {
            if (checkCount == 0)
            {
                if (uid==6){
                    return userModelService.updateUser(uid, userName, password)-2;
                }else {
                    return userModelService.updateUser(uid, userName, password);
                }

            } else
            {
                return 0;
            }
        }

    }

    @RequestMapping("/selectUserByName")
    @ResponseBody
    public JSONObject selectUserByName(HttpServletRequest request, Model model,String username,int nowPage, int pageSize)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据用户名分页查询用户
         * @Date: 16:15 2016/12/7
         * @param request
         * @param model
         * @Return:
         */
        username=username.replaceAll(" ","");
        log.info("根据用户名分页查询用户");
        HttpSession session = request.getSession();
        int urole = (Integer) session.getAttribute(UROLE);
        return userModelService.getUsersByUname(username,pageSize,nowPage,urole);
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    public JSONObject getAllUsers(HttpServletRequest request, Model model, int nowPage, int pageSize)
    {
        /**
         * @Author: 马潇霄
         * @Description:获取全部用户分页显示
         * @Date: 13:26 2016/12/7
         * @param request
         * @param model
         */
        log.info("分页显示用户");
        HttpSession session = request.getSession();
        int urole = (Integer) session.getAttribute(UROLE);
        return userModelService.getAllUser(pageSize,nowPage,urole);
    }

    @RequestMapping("/deleteUserByIds")
    @ResponseBody
    public int  deleteUserByIds(HttpServletRequest request, Model model,String userIdList)
    {
        /**
         * @Author: 马潇霄
         * @Description: 删除多个用户
         * @Date: 13:26 2016/12/7
         * @param request
         * @param model
         */
        log.info("删除用户");
        String[] arr = userIdList.split("/");

        List<Integer> list = new ArrayList<Integer>();
        List<String> listString = Arrays.asList(arr);
        for (int i = 0; i < listString.size(); i++)
        {
            list.add(Integer.valueOf(listString.get(i)));
        }

        HttpSession session = request.getSession();
        int urole = (Integer) session.getAttribute(UROLE);
        if (urole==2){
            return -5;
        }else {
            if(list.contains(6)){
                return -1;
            }else {
                return userModelService.deleteUserByIds(list);
            }
        }


    }
}
