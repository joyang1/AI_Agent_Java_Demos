package com.ai.agent.demos.customchat.handler;

import com.ai.agent.demos.enums.ChatTypeEnum;
import com.ai.agent.demos.functioncall.WeatherExecutor;
import com.ai.agent.demos.functioncall.WeatherQueryTools;
import com.ai.agent.demos.service.Assistant;
import com.ai.agent.demos.service.LangChain4jFunctionCallBackService;
import com.ai.agent.demos.utils.prompt.PromptUtils;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.*;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ToolChoice;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 使用langchain4j实现工具调用
 *
 * <a href="https://docs.langchain4j.dev/tutorials/tools">langchain4j工具调用</a>
 *
 * @author tommy
 * @Time 2025年08月17日 12:30
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName FunctionChatHandler.java
 */
@Component("functionCall")
@RequiredArgsConstructor
public class LangChain4jFunctionChatHandler implements ChatHandler {

    final ChatModel chatModel;

    @Autowired
    private WeatherExecutor weatherExecutor;

    @Autowired
    private LangChain4jFunctionCallBackService langChain4jFunctionCallBackService;

    @Override
    public String chat(String message) {
        String aiMessage = "";
        // 手动执行函数调用流程
//        aiMessage = functionCallManual(message);

        // 自动执行函数调用流程
        aiMessage = functionCallAuto(message);

        return aiMessage;
    }

    /**
     * 手动执行函数调用流程
     *
     * @param message 用户输入的消息内容
     * @return 模型最终返回的文本结果
     */
    private String functionCallManual(String message) {
        // 构建用户消息和系统消息
        ChatMessage userMessage = UserMessage.from(message);
        ChatMessage systemMessage = SystemMessage.from(PromptUtils.getFunctionCallPrompt());

        // 构建首次聊天请求，包含工具规范和自动工具选择
        ChatRequest request = ChatRequest.builder()
                .messages(systemMessage, userMessage)
                .toolSpecifications(ToolSpecifications.toolSpecificationsFrom(WeatherQueryTools.class))
                .toolChoice(ToolChoice.AUTO)
                .build();

        // 发送首次请求并获取AI回复
        AiMessage aiMessage = this.chatModel.chat(request).aiMessage();

        // 手动执行工具调用
        if (aiMessage.hasToolExecutionRequests()) {
            // 执行天气查询工具并获取结果
            ToolExecutionResultMessage toolExecutionResultMessage = langChain4jFunctionCallBackService
                    .weatherQueryCallBack(aiMessage.toolExecutionRequests().get(0));

            // 构建第二次聊天请求，将工具执行结果传回给模型
            ChatRequest requestNew = ChatRequest.builder()
                    .messages(systemMessage, userMessage, toolExecutionResultMessage)
                    .toolSpecifications(ToolSpecifications.toolSpecificationsFrom(WeatherQueryTools.class))
                    .toolChoice(ToolChoice.AUTO)
                    .build();

            // 发送第二次请求获取最终结果
            aiMessage = this.chatModel.chat(requestNew).aiMessage();
        }

        return aiMessage.text();
    }

    /**
     * 自动执行函数调用并获取上海天气信息
     *
     * @param message 输入的消息内容，用于传递查询参数
     * @return 返回AI助手对天气查询的响应结果
     */
    private String functionCallAuto(String message) {
        // 构建工具映射表，将天气查询工具规范与对应的执行器进行映射
        Map<ToolSpecification, ToolExecutor> ToolMap = Map.of(
                WeatherQueryTools.getWeatherQueryTool(),
                weatherExecutor
        );

        // 借助AiServices自动执行工具调用
        // Assistant 接口自己实现一个定义就行，参考：
        return AiServices.builder(Assistant.class)
                .chatModel(this.chatModel)
                .tools(ToolMap)
                .build()
                .chat(message);
    }

    @Override
    public String type() {
        return ChatTypeEnum.FUNCTION_CALL.getType();
    }
}
