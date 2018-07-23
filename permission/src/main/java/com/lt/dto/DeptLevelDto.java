package com.lt.dto;

import com.google.common.collect.Lists;
import com.lt.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by taoshiliu on 2018/7/23.
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept{
    //下一层的部门
    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(dept,deptLevelDto);
        return deptLevelDto;
    }
}
