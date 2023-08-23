package com.example.springbootdbsources.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdbsources.config.annotations.NotEncryptResponse;
import com.example.springbootdbsources.pojo.po.DictDataSlave1;
import com.example.springbootdbsources.service.DictDataSlave1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/dict_data_slave1")
@RequiredArgsConstructor

public class DictDataSlave1Controller {

    private final DictDataSlave1Service dictDataSlave1Service;

    @DS("slave_1")
    @NotEncryptResponse
    @GetMapping("/getPageList/v1")
    public Page<DictDataSlave1> getPageList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return dictDataSlave1Service.lambdaQuery().page(new Page<>(pageNum, pageSize));
    }

}
