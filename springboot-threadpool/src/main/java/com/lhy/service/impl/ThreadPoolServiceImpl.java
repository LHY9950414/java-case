package com.lhy.service.impl;

import com.lhy.service.ThreadPoolService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liheyan
 */
@Slf4j
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

    @Async("asyncServiceExecutor")
    @Override
    @SneakyThrows
    public void sendSync() {
        log.info("异步开始");
        Thread.sleep(10000);
        log.info("异步结束");
    }
}
