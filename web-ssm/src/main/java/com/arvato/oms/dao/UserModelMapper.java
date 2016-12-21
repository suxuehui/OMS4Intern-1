package com.arvato.oms.dao;

import com.arvato.oms.model.UsersModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */

@Repository
public interface UserModelMapper
{
    List<UsersModel> selectUserByNameAndPWD(@Param("uName") String uName, @Param("uPassword") String uPassword);
    //查询指定用户名，密码的用户（用于检验用户名密码是否匹配）

    int insertUser(@Param("uName") String uName, @Param("uPassword") String uPassword);
    //添加用户

    List<UsersModel> selectUserByName(@Param("uName") String uName);
    //根据用户名查找用户

    int updateUser(@Param("uId")int uId,@Param("uName") String uName, @Param("uPassword") String uPassword);
    //更新用户信息

    List<UsersModel> selectUserByNameAndPage(@Param("uName") String uName,@Param("startPage")int page,@Param("num") int num);
    //根据用户名分页查询用户

    List<UsersModel> selectAllUser(@Param("startPage")int page,@Param("num") int num);
    //分页查询全部用户

    int countUser();
    //统计用户个数

    int deleteUserById(@Param("uIds")List<Integer> uId);
    //根据id删除用户

    UsersModel selectOneUserByName(@Param("uname")String uname);
    //根据用户名查询用户（用于查询用户角色）

    int countSelectUser(@Param("uName") String uName);
    //统计查询用户个数

}
