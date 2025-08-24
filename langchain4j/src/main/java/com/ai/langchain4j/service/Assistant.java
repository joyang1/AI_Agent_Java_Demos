package com.ai.langchain4j.service;

/**
 * AI service chat 助手
 * 参考：<a href="https://docs.langchain4j.dev/tutorials/ai-services">langchain4j AiServices</a>
 *
 * @author tommy
 * @Time 2025年08月17日 11:25
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName Assistant.java
 */
public interface Assistant {

    /**
     * 与AI助手进行聊天交互
     *
     * @param message 用户发送的消息
     * @return AI助手的回复内容
     */
    String chat(String message);

}
