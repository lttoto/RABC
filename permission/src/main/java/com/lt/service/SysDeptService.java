package com.lt.service;

import com.google.common.base.Preconditions;
import com.lt.dao.SysDeptMapper;
import com.lt.exception.ParamException;
import com.lt.model.SysDept;
import com.lt.param.DeptParam;
import com.lt.util.BeanValidator;
import com.lt.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public void update(DeptParam param) {
        BeanValidator.check(param);
        if(this.checkExist(param.getParentId(),param.getName(),param.getId())) {
            throw new ParamException("同一层级下存在项目名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的部门不存在");
        SysDept after = SysDept.builder().id(param.getId()).name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator("system-update"); //TODO
        after.setOperateIp("127.0.0.1");//TODO
        after.setOperateTime(new Date());

        this.updateWithChild(before,after);
    }

    @Transactional
    private void updateWithChild(SysDept before,SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if(CollectionUtils.isNotEmpty(deptList)) {
                for(SysDept sysDept: deptList) {
                    String level = sysDept.getLevel();
                    if(level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    private boolean checkExist(Integer parentId,String deptName,Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId,deptName,deptId) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
