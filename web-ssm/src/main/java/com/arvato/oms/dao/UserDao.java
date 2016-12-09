package com.arvato.oms.dao;

import com.arvato.oms.model.UserTest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    UserTest selectUserById(@Param("userId") Long userId);

    UserTest selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    List<UserTest> selectAllUser();
}
