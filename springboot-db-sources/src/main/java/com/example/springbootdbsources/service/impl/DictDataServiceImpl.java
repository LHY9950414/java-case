package com.example.springbootdbsources.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdbsources.pojo.po.DictData;
import com.example.springbootdbsources.service.DictDataService;
import com.example.springbootdbsources.pojo.dao.DictDataMapper;
import org.springframework.stereotype.Service;

/**
* @author liheyan
* @description 针对表【dict_data(数据字典表)】的数据库操作Service实现
* @createDate 2023-08-09 23:47:29
*/
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData>
    implements DictDataService{

}




