package com.example.springbootdbsources.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdbsources.pojo.dao.DictDataSlave1Mapper;
import com.example.springbootdbsources.pojo.po.DictDataSlave1;
import com.example.springbootdbsources.service.DictDataSlave1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author liheyan
* @description 针对表【dict_data_slave1(数据字典表)】的数据库操作Service实现
* @createDate 2023-08-10 00:01:06
*/
@Service
@RequiredArgsConstructor
public class DictDataSlave1ServiceImpl extends ServiceImpl<DictDataSlave1Mapper, DictDataSlave1>
    implements DictDataSlave1Service{

}




