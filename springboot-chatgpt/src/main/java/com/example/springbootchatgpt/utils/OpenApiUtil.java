package com.example.springbootchatgpt.utils;

import com.example.springbootchatgpt.config.openapi.OpenApiConfig;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenApiUtil {

    private final OpenAiService openAiService;
    private final OpenApiConfig openApiConfig;

    public String getChat(String prompt){
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(openApiConfig.getModel())
                .echo(true)
                .temperature(0.7)
                .topP(1d)
                .frequencyPenalty(0d)
                .presencePenalty(0d)
                .maxTokens(1000)
                .build();
        CompletionResult completionResult = openAiService.createCompletion(completionRequest);
        String text = completionResult.getChoices().get(0).getText();
        return text.substring(text.indexOf("\n\n") + 2);
    }
}
