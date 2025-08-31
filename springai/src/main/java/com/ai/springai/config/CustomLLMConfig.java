package com.ai.springai.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tommy
 * @Time 2025年09月01日 00:31
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName CustomLLMConfig.java
 */
@Configuration
public class CustomLLMConfig {
    @Bean("springAIOllamaChatModel")
    public OllamaChatModel springAiOllamaChatModel() {
        OllamaOptions options = OllamaOptions.builder()
                .model("qwen3:1.7b")
                .temperature(0.7)
                .build();
        OllamaApi ollamaApi = new OllamaApi();

        return OllamaChatModel.builder().ollamaApi(ollamaApi).defaultOptions(options).build();
    }
}
