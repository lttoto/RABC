package com.lt.service;

import com.lt.dao.SysDeptMapper;
import com.lt.exception.ParamException;
import com.lt.model.SysDept;
import com.lt.param.DeptParam;
import com.lt.util.BeanValidator;
import com.lt.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by taoshiliu on 2018/7/23.
 */
@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if(this.checkExist(param.getParentId(),param.getName(),param.getId())) {
            throw new ParamException("同一层级下存在项目名称的部门");
        }
        SysDept dept = SysDept.builder().name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        dept.setLevel(LevelUtil.calculateLevel(this.getLevel(param.getParentId()),param.getParentId()));
        dept.setOperator("system"); //TODO
        dept.setOperateIp("127.0.0.1");//TODO
        dept.setOperateTime(new Date());
        sysDeptMapper.insert(dept);
    }

    private boolean checkExist(Integer parentId,String deptName,Integer deptId) {
        //TODO
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
