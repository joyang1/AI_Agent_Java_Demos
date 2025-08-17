package com.ai.agent.demos.customchat;

import com.ai.agent.demos.customchat.handler.ChatHandler;
import com.ai.agent.demos.enums.ChatTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tommy
 * @Time 2025年05月05日 23:57
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName CustomChatAgent.java
 */
@Component
public class CustomChatAgent {

    @Autowired
    Map<String, ChatHandler> chatHandlerMap;

    /**
     * 简单聊天
     *
     * @param message 消息
     * @param typeEnum 类型
     * @return
     */
    public String chat(String message, ChatTypeEnum typeEnum) {
        ChatHandler chatHandler = chatHandlerMap.get(typeEnum.getType());
        return chatHandler.chat(message);
    }

}
