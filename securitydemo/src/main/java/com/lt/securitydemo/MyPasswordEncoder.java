package com.lt.securitydemo;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by taoshiliu on 2018/7/22.
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //Md5Encoder + Salt
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //isPasswordValid(new,old,salt)
        return false;
    }
}
