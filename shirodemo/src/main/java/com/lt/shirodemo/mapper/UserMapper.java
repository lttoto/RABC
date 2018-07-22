package com.lt.shirodemo.mapper;

import com.lt.shirodemo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by taoshiliu on 2018/7/22.
 */
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
