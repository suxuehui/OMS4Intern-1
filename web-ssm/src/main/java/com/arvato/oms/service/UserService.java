package com.arvato.oms.service;

import com.arvato.oms.model.UserTest;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {

    List<UserTest> getAllUser();

    UserTest getUserByPhoneOrEmail(String emailOrPhone, Short state);

    UserTest getUserById(Long userId);
}
