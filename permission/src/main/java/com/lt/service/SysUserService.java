package com.lt.service;

import com.google.common.base.Preconditions;
import com.lt.dao.SysUserMapper;
import com.lt.exception.ParamException;
import com.lt.model.SysUser;
import com.lt.param.UserParam;
import com.lt.util.BeanValidator;
import com.lt.util.MD5Util;
import com.lt.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by taoshiliu on 2018/7/24.
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param) {
        BeanValidator.check(param);
        if(this.checkTelephoneExist(param.getTelephone(),param.getId())) {
            throw new ParamException("电话已经被占用");
        }
        if(this.checkEmailExist(param.getMail(),param.getId())) {
            throw new ParamException("邮箱已经被占用");
        }
        String password = PasswordUtil.randomPassword();
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser sysUser = SysUser.builder().username(param.getUsername())
                .telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId())
                .status(param.getStatus()).remark(param.getRemark()).build();
        sysUser.setOperator("system"); //TODO
        sysUser.setOperateIp("127.0.0.1");//TODO
        sysUser.setOperateTime(new Date());

        //TODO:sendEmail
        sysUserMapper.insert(sysUser);
    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if(this.checkTelephoneExist(param.getTelephone(),param.getId())) {
            throw new ParamException("电话已经被占用");
        }
        if(this.checkEmailExist(param.getMail(),param.getId())) {
            throw new ParamException("邮箱已经被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername())
                .telephone(param.getTelephone()).mail(param.getMail())
                .deptId(param.getDeptId())
                .status(param.getStatus()).remark(param.getRemark()).build();
        sysUserMapper.updateByPrimaryKeySelective(after);

    }

    public boolean checkEmailExist(String mail,Integer userId) {
        return sysUserMapper.countByMail(mail,userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone,Integer userId) {
        return sysUserMapper.countByTelephone(telephone,userId) > 0;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }
}
