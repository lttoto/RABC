package com.lt.util;

import java.util.Date;
import java.util.Random;

/**
 * Created by taoshiliu on 2018/7/24.
 */
public class PasswordUtil {

    public final static String[] word = {
            "a","b","c","d","e","f","g","h","j","k","m","n","p","q"
    };

    public final static String[] num = {
            "2","3"
    };

    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());

        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for(int i = 0;i < length;i++) {
            if(flag == true) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            }else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }
}
