package com.example.springbootdbsources.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 * @author liheyan
 */
@Configuration
@MapperScan("com.example.springbootdbsources.pojo.dao")
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // mybatis-plus拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // mysql 分页拦截
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
