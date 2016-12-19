package com.arvato.oms.dao;

import com.arvato.oms.model.UsersModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MAVI003 on 2016/12/6.
 */

@Service
public interface UserModelMapper
{
    List<UsersModel> selectUserByNameAndPWD(@Param("uName") String uName, @Param("uPassword") String uPassword);

    int insertUser(@Param("uName") String uName, @Param("uPassword") String uPassword);

    List<UsersModel> selectUserByName(@Param("uName") String uName);

    int updateUser(@Param("uId")int uId,@Param("uName") String uName, @Param("uPassword") String uPassword);

    List<UsersModel> selectUserByNameAndPage(@Param("uName") String uName,@Param("startPage")int page,@Param("num") int num);

    List<UsersModel> selectAllUser(@Param("startPage")int page,@Param("num") int num);

    int countUser();

    void deleteUserById(@Param("uIds")List<Integer> uId);


}
