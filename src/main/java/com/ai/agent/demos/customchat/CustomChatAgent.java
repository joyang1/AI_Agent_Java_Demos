package com.ai.agent.demos.customchat;

import com.alibaba.dashscope.common.Message;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tommy
 * @Time 2025年05月05日 23:57
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName CustomChatAgent.java
 */
@Component
@RequiredArgsConstructor
public class CustomChatAgent {

    final ChatModel chatModel;

    /**
     * 简单聊天
     *
     * @param message
     * @return
     */
    public String chat(String message) {
        List<ChatMessage> messages = List.of(
                SystemMessage.systemMessage("现在你是篮球明星詹姆斯，接下来请使用詹姆斯的语气跟我对话"),
                UserMessage.userMessage(message));
        return chatModel.chat(messages).aiMessage().text();
    }

}
