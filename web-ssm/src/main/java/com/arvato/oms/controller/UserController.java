package com.arvato.oms.controller;

import com.arvato.oms.model.UsersModel;
import com.arvato.oms.service.UserModelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */

@Controller
@RequestMapping("/user")
public class UserController
{

    private Logger log = Logger.getLogger(UserController.class);


    @Resource
    private UserModelService userModelService;

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, Model model)
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
        int checkCount = userModelService.checkUname("as");//用户名是否存在状态码，0为不存在，1为存在，
        // 2为多条同名用户（异常情况）
        int i = 0;//初始值为0，添加成功为1
        if (checkCount == 0)
        {
            i = userModelService.addUser("as", "sad");

            model.addAttribute("i", i);
        } else
        {
            model.addAttribute("i", 0);
        }

        return "showUser2";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request, Model model)
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
        int i = userModelService.updateUser(1, "mxx", "mxx1");
        model.addAttribute("i", i);
        return "showUser2";
    }

    @RequestMapping("/selectUserByName")
    public String selectUserByName(HttpServletRequest request, Model model)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据用户名分页查询用户
         * @Date: 16:15 2016/12/7
         * @param request
         * @param model
         * @Return:
         */
        log.info("根据用户名分页查询用户");

        List<UsersModel> as = userModelService.getUsersByUname("马潇霄", 0, 3);
        System.out.print(as.size());

        model.addAttribute("as", as);
        return "showUser2";
    }

    @RequestMapping("/getAllUsers")
    public String getAllUsers(HttpServletRequest request, Model model)
    {
        /**
         * @Author: 马潇霄
         * @Description:获取全部用户分页显示
         * @Date: 13:26 2016/12/7
         * @param request
         * @param model
         */
        log.info("分页显示用户");

        List<UsersModel> as = userModelService.getAllUser(3, 1);
        System.out.print(as.size());

        model.addAttribute("as", as);
        return "showUser2";
    }

    @RequestMapping("/deleteUserByIds")
    //
    public String deleteUserByIds(HttpServletRequest request, Model model)
    {
        /**
         * @Author: 马潇霄
         * @Description: 删除多个用户
         * @Date: 13:26 2016/12/7
         * @param request
         * @param model
         */
        log.info("删除用户");
        List<Integer> list = new ArrayList<Integer>();
        list.add(11);
        list.add(12);
        userModelService.deleteUserByIds(list);

        return "showUser2";
    }
}
