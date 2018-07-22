package com.lt.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by taoshiliu on 2018/7/23.
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull
    private Integer id;

    @NotEmpty
    private List<String> str;
}
