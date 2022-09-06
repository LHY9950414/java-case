package com.lhy.service.impl;

import com.lhy.pojo.po.User;
import com.lhy.pojo.dao.UserMapper;
import com.lhy.Service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liheyan
 * @since 2022-09-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
