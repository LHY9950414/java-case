package com.lhy.controller;

import com.lhy.config.common.Constants;
import com.lhy.config.common.Result;
import com.lhy.service.ThreadPoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liheyan
 */
@Slf4j
@RestController
@RequestMapping("/api/threadPool")
@RequiredArgsConstructor
public class ThreadPoolController {

    private final ThreadPoolService threadPoolService;

    @PostMapping("/pool")
    public Result ThreadPoolController(){
        for (int i = 0; i < Constants.NINE; i++) {
            threadPoolService.sendSync();
        }
        return Result.success();
    }
}
