package com.ai.agent.demos.customchat.handler;

/**
 * @author tommy
 * @Time 2025年08月17日 12:28
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName AbstractHandler.java
 */
public interface ChatHandler {

    /**
     * 聊天处理
     *
     * @param message 用户消息
     * @return 模型响应
     */
    String chat(String message);

    /**
     * 获取处理类型
     *
     * @return 处理类型
     */
    String type();

}
