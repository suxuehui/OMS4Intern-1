package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.controller.UserController;
import com.arvato.oms.dao.UserModelMapper;
import com.arvato.oms.model.UsersModel;
import com.arvato.oms.service.UserModelService;
import com.arvato.oms.utils.Page;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserModelServiceImpl implements UserModelService
{

    @Resource
    UserModelMapper userModelMapper;

    private Logger log = Logger.getLogger(UserModelServiceImpl.class);

    public int login(String uName, String uPassword)
    {
        /**
         * @Author: 马潇霄
         * @Description: 判断用户名密码是否匹配，返回0表示不匹配
         * @Date: 16:05 2016/12/7
         * @param uName 用户名
         * @param uPassword 用户密码
         * @Return: int 1表示超级管理员，2表示普通管理员，3表示既不是超级管理员，又不是普通管理员（基本不会出现，除非数据库填错了）
         */
        List<UsersModel> users = userModelMapper.selectUserByNameAndPWD(uName, uPassword);
        if (users.size() == 0)
        {
            return 0;
        } else
        {
            if (users.get(0).getUrole().equals("1"))
            {
                return 1;

            } else if (users.get(0).getUrole().equals("2"))
            {
                return 2;
            }
            return 3;
        }
    }


    public int addUser(String uName, String uPassword)
    {
        /**
         * @Author: 马潇霄
         * @Description: 添加用户
         * @Date: 16:00 2016/12/7
         * @param uName 用户名
         * @param uPassword 用户密码
         * @Return: int 1为添加成功，2为添加失败
         */
        int i = userModelMapper.insertUser(uName, uPassword);
        return i;
    }

    public int checkUname(String uName)
    {
        /**
         * @Author: 马潇霄
         * @Description: 判断用户名存在
         * @Date: 15:59 2016/12/7
         * @param uName
         * @Return: int 0为不存在，1为存在， 2为数据库有多条同名用户（异常情况）
         */
        List<UsersModel> usersModels = userModelMapper.selectUserByName(uName);
        log.info("查询用户个数："+usersModels.size());
        if (usersModels.isEmpty())
        {
            return 0;
        } else if (usersModels.size() == 1)
        {
            return 1;
        } else
        {
            return 2;
        }
    }

    public int updateUser(int uId, String uName, String uPassword)
    {
        /**
         * @Author: 马潇霄
         * @Description: 修改用户名，密码
         * @Date: 16:07 2016/12/7
         * @param uId
         * @param uName
         * @param uPassword
         * @Return: int 1表示修改成功，0表示修改失败
         */
        int i = userModelMapper.updateUser(uId, uName, uPassword);
        return i;
    }

    public JSONObject getUsersByUname(String name, int num, int pageNow)
    {
        /**
         * @Author: 马潇霄
         * @Description: 按用户名模糊查询用户
         * @Date: 15:41 2016/12/7
         * @param name 用户名
         * @param num 每页需要显示的用户信息条数
         * @param pageNow 现在的页码
         * @Return: List<UsersModel>
         */
        Integer countUser = userModelMapper.countSelectUser(name);
        Page page = new Page(pageNow,num);
        List<UsersModel> usersModels = userModelMapper.selectUserByNameAndPage(name,page.getStartPos(),num);
        log.info("StartPos:"+page.getStartPos());
        JSONObject json = new JSONObject();
        json.put("userList",usersModels);
        json.put("totalPage",page.getTotalPageCount(countUser));
        return json;
    }

    public JSONObject getAllUser(int num, int pageNow)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页显示全部用户信息
         * @Date: 15:42 2016/12/7
         * @param num 每页需要显示的用户信息条数
         * @param pageNow 现在的页码
         * @Return: List<UsersModel>
         */
        Integer countUser = userModelMapper.countUser();
        Page page = new Page(pageNow,num);

        List<UsersModel> usersModels = userModelMapper.selectAllUser(page.getStartPos(),num);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userList",usersModels);
        jsonObject.put("totalPage",page.getTotalPageCount(countUser));
        return jsonObject;
    }

    public List<UsersModel> getAllUserFirstPage(int num)
    {//暂不用
        List<UsersModel> usersModels = userModelMapper.selectAllUser(0, num);
        return usersModels;
    }

    public List<UsersModel> getAllUserEndPage(int num)
    {//暂不用
        return null;
    }

    public int deleteUserByIds(List<Integer> uIds)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据用户Id删除用户信息
         * @Date: 16:11 2016/12/7
         * @param uIds 用户Id
         */

        return  userModelMapper.deleteUserById(uIds);
    }

    public boolean isAdmin(String uname)
    {
        /**
         * @Author: 马潇霄
         * @Description: 判断用户是否是管理员 ，
         * @Date: 10:55 2016/12/16
         * @param uname
         * @Return: boolean :true 为管理员， false为非管理员
         */
        UsersModel usersModel = userModelMapper.selectOneUserByName(uname);
        String urole = usersModel.getUrole();//1表示管理员，2表示普通用户
        if (urole.equals("1"))
        {
            return true;
        } else
        {
            return false;
        }
    }


}
