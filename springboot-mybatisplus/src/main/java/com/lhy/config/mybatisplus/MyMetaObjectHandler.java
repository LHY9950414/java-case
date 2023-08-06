package com.lhy.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元数据处理
 * @author liheyan
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 所有insert mysql数据源的sql执行后,默认赋值以下属性
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date(System.currentTimeMillis());
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
    }

    /**
     * 所有update mysql数据源的sql执行后,默认赋值以下属性
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date(System.currentTimeMillis());
        this.setFieldValByName("updateTime", date, metaObject);
    }
}
