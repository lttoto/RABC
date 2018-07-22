package com.lt.shirodemo.serivce;

import com.lt.shirodemo.model.User;

/**
 * Created by taoshiliu on 2018/7/22.
 */
public interface UserService {

    User findByUsername(String username);
}
