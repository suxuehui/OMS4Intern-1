package com.arvato.oms.service.impl;

import com.arvato.oms.dao.UserDao;
import com.arvato.oms.model.UserTest;
import com.arvato.oms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;

    public UserTest getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }
    
    public UserTest getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
    }
    
    public List<UserTest> getAllUser() {
        return userDao.selectAllUser();
    }
}
