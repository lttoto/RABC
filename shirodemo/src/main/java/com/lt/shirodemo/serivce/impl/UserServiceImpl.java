package com.lt.shirodemo.serivce.impl;

import com.lt.shirodemo.mapper.UserMapper;
import com.lt.shirodemo.model.User;
import com.lt.shirodemo.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taoshiliu on 2018/7/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
