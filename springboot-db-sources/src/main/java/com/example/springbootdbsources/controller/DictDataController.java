package com.example.springbootdbsources.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdbsources.pojo.po.DictData;
import com.example.springbootdbsources.service.DictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/dict_data")
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

    @GetMapping("/getPageList/v1")
    public Page<DictData> getPageList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return dictDataService.lambdaQuery().page(new Page<>(pageNum, pageSize));
    }

}
