package com.ai.agent.demos.customchat;

import com.ai.agent.demos.functioncall.WeatherExecutor;
import com.ai.agent.demos.functioncall.WeatherQueryTools;
import com.ai.agent.demos.service.Assistant;
import com.ai.agent.demos.utils.FunctionCallUtil;
import com.ai.agent.demos.utils.prompt.PromptUtils;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.*;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ToolChoice;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolExecutor;

import java.util.Map;

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

    public CustomChatAgentCLI(String modelName) {
        this.chatModel = OllamaChatModel.builder().baseUrl("http://127.0.0.1:11434").modelName(modelName).build();
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
     * 基础聊天
     *
     * @param chatRequest 聊天请求
     * @return 模型返回
     */
    public ChatResponse chat(ChatRequest chatRequest) {
        return this.chatModel.chat(chatRequest);
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
        // hello agent
//        agentHello();

        // function call 手动执行案例
        withFunctionCall_Manual();

        // function call 自动执行案例
//        withFunctionCall_Auto();
    }

    /**
     * 本地Agent执行 hello world
     */
    private static void agentHello() {
        System.out.println(new CustomChatAgentCLI().chat("你是谁？"));
    }

    /**
     * Function Call 手动执行案例
     */
    private static void withFunctionCall_Manual() {
        CustomChatAgentCLI chatAgentCLI = new CustomChatAgentCLI("qwen3:1.7b");
        ChatMessage userMessage = UserMessage.from("上海市今天天气多少度");
        ChatMessage systemMessage = SystemMessage.from(PromptUtils.getFunctionCallPrompt());

        ChatRequest request = ChatRequest.builder()
                .messages(systemMessage, userMessage)
                .toolSpecifications(ToolSpecifications.toolSpecificationsFrom(WeatherQueryTools.class))
                .toolChoice(ToolChoice.AUTO)
                .build();

        AiMessage aiMessage = chatAgentCLI.chat(request).aiMessage();
        if (aiMessage.hasToolExecutionRequests()) {
            ToolExecutionResultMessage toolExecutionResultMessage = FunctionCallUtil.weatherQueryCallBack(aiMessage.toolExecutionRequests().get(0));
            ChatRequest requestNew = ChatRequest.builder()
                    .messages(systemMessage, userMessage, toolExecutionResultMessage)
                    .toolSpecifications(ToolSpecifications.toolSpecificationsFrom(WeatherQueryTools.class))
                    .toolChoice(ToolChoice.AUTO)
                    .build();
            aiMessage = chatAgentCLI.chat(requestNew).aiMessage();
        }

        System.out.println(aiMessage.text());
    }


    /**
     * Function Call 自动执行案例
     * 借助AI Services
     */
    private static void withFunctionCall_Auto() {
        CustomChatAgentCLI chatAgentCLI = new CustomChatAgentCLI("qwen3:1.7b");

        Map<ToolSpecification, ToolExecutor> ToolMap = Map.of(
                WeatherQueryTools.getWeatherQueryTool(),
                new WeatherExecutor()
        );

        String response = AiServices.builder(Assistant.class)
                .chatModel(chatAgentCLI.chatModel)
                .tools(ToolMap)
                .build()
                .chat("上海今天天气多少度/nothink");

        System.out.println(response);
    }
}
