package com.arvato.oms.service;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.model.UsersModel;

import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */
public interface UserModelService
{

    public int login(String uName, String uPassword);
    //登陆验证用户名密码是否正确

    public int addUser(String uName, String uPassword);
    //添加用户

    public int checkUname(String uName);
    //查询用户名是否存在

    public int updateUser(int uId, String uName, String uPassword);
    //更新用户信息

    public JSONObject getUsersByUname(String name, int num, int page);
    //根据用户名分页查询用户信息

    public JSONObject getAllUser(int num, int pageNow);
    //分页查询全部用户信息

    public List<UsersModel> getAllUserFirstPage(int num);
    //暂不用

    public List<UsersModel> getAllUserEndPage(int num);
    //暂不用

    public int deleteUserByIds(List<Integer> uIds);
    //根据id删除用户信息

    boolean isAdmin(String uname);
    //判断用户名是否是管理员

}
