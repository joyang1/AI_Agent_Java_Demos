package com.ai.springai.handler;

import com.ai.common.enums.ChatTypeEnum;
import com.ai.common.handler.ChatHandler;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tommy
 * @Time 2025年08月31日 22:46
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName SpringAIFunctionChatHandler.java
 */
@Component("springAI")
public class SpringAIFunctionChatHandler implements ChatHandler {
    @Autowired
    private OllamaChatModel springAIOllamaChatModel;

    @Override
    public String chat(String message) {
        return springAIOllamaChatModel.call(message);
    }

    @Override
    public String type() {
        return ChatTypeEnum.SPRING_AI.getType();
    }
}
