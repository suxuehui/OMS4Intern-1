package com.arvato.oms.service;

import com.arvato.oms.model.UserTest;
import com.arvato.oms.model.UsersModel;

import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */
public interface UserModelService
{

    public int login(String uName, String uPassword);

    public int addUser(String uName, String uPassword);

    public int checkUname(String uName);

    public int updateUser(int uId, String uName, String uPassword);

    public List<UsersModel> getUsersByUname(String name, int num, int page);

    public List<UsersModel> getAllUser(int num, int pageNow);

    public List<UsersModel> getAllUserFirstPage(int num);

    public List<UsersModel> getAllUserEndPage(int num);

    public void deleteUserByIds(List<Integer> uIds);

}
