package com.lt.controller;

import com.lt.common.JsonData;
import com.lt.dto.DeptLevelDto;
import com.lt.param.DeptParam;
import com.lt.service.SysDeptService;
import com.lt.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by taoshiliu on 2018/7/23.
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {


    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> dtos = sysTreeService.deptTree();
        return JsonData.success(dtos);
    }
}
