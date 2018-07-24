package com.lt.controller;

import com.lt.model.SysUser;
import com.lt.service.SysUserService;
import com.lt.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by taoshiliu on 2018/7/24.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser user = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");

        if(StringUtils.isBlank(username)) {
            errorMsg = "用户名不可以为空";
        }else if(StringUtils.isBlank(password)) {
            errorMsg = "密码不可以为空";
        }else if(user == null) {
            errorMsg = "用户不存在";
        }else if(!user.getPassword().equals(MD5Util.encrypt(password))) {
            errorMsg = "密码不正确";
        }else if(user.getStatus() != 1) {
            errorMsg = "用户名已冻结";
        }else {
            //login succcess
            request.getSession().setAttribute("user",user);
            if(StringUtils.isNoneBlank(ret)) {
                response.sendRedirect(ret);
            }else {
                response.sendRedirect("/admin/index.page");
            }
        }

        request.setAttribute("error",errorMsg);
        request.setAttribute("username",username);
        if(StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret",ret);
        }
        String path = "signin.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
