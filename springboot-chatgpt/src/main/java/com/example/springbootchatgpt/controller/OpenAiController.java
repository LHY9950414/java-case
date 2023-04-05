package com.example.springbootchatgpt.controller;

import com.example.springbootchatgpt.config.common.Result;
import com.example.springbootchatgpt.utils.OpenApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
@CrossOrigin
@Slf4j
public class OpenAiController {

    private final OpenApiUtil openApiUtil;

    @RequestMapping(method = RequestMethod.GET, value = "/01")
    public Result testOpenApi (@RequestParam("prompt") String prompt){
        log.info("testOpenApi request ->>> {}", prompt);
        String chat = openApiUtil.getChat(prompt);
        log.info("testOpenApi respinse ->>> {}", chat);
        return new Result(chat);
    }
}
