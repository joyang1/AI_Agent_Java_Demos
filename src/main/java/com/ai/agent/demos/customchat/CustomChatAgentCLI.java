package com.ai.agent.demos.customchat;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

/**
 * @author tommy
 * @Time 2025年05月05日 23:57
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName CustomChatAgentCLI.java
 */
public class CustomChatAgentCLI {

    final ChatModel chatModel;

    private static final String MODEL_NAME = "deepseek-r1:8b";

    private static final String MODEL_TYPE = "deepseek";

    /**
     * 设置chatModel
     */
    public CustomChatAgentCLI() {
        this.chatModel = OllamaChatModel.builder().baseUrl("http://127.0.0.1:11434").modelName(MODEL_NAME).build();
    }

    /**
     * 基础聊天
     *
     * @param userInput 用户输入
     * @return 模型返回
     */
    public String chat(String userInput) {
        return this.chatModel.chat(userInput);
    }

    /**
     * 获取模型类型
     *
     * @return 模型类型
     */
    public String getModelType() {
        return MODEL_TYPE;
    }

    public static void main(String[] args) {
        System.out.println(new CustomChatAgentCLI().chat("你是谁？"));
    }
}
