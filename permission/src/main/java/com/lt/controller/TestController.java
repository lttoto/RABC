package com.lt.controller;

import com.lt.common.JsonData;
import com.lt.exception.ParamException;
import com.lt.exception.PermissionException;
import com.lt.param.TestVo;
import com.lt.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by taoshiliu on 2018/7/23.
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new RuntimeException("test exception");
        //return JsonData.success("hello permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        log.info("validate");
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
