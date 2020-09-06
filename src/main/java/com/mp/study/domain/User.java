package com.mp.study.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version
    //这个不写可以，写是为了让人在数据库中看的更加直观，不然version字段为null,一改，没有对比看不出变化
    @TableField(fill = FieldFill.INSERT)
    private Integer version; //版本号

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
