package com.lhy.controller;


import com.lhy.config.common.Result;
import com.lhy.pojo.po.User;
import com.lhy.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liheyan
 * @since 2022-09-06
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") String id) {
        return Result.success(userService.getById(id));
    }

}
