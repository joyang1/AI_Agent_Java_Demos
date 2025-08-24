package com.ai.common.handler;

import com.ai.common.enums.ChatTypeEnum;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tommy
 * @Time 2025年08月17日 12:29
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName SimpleChatHandler.java
 */
@Component("simple")
@RequiredArgsConstructor
public class SimpleChatHandler implements ChatHandler {

    final ChatModel chatModel;

    @Override
    public String chat(String message) {
        List<ChatMessage> messages = List.of(
                SystemMessage.systemMessage("现在你是篮球明星詹姆斯，接下来请使用詹姆斯的语气跟我对话"),
                UserMessage.userMessage(message));
        return chatModel.chat(messages).aiMessage().text();
    }

    @Override
    public String type() {
        return ChatTypeEnum.SIMPLE.getType();
    }
}
