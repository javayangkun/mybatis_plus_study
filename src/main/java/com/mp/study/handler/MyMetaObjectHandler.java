package com.mp.study.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
//mybatis——plus实现自动填充需要实现的类和方法
import java.util.Date;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //本类的方法
        this.setFieldValByName("createTime",new Date(),metaObject);
        setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("version",1,metaObject);
        this.setFieldValByName("deleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
       this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
