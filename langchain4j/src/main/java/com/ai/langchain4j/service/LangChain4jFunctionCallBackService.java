package com.ai.langchain4j.service;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.ToolExecutionResultMessage;

/**
 * langchain4j工具回调服务
 *
 * @author tommy
 * @Time 2025年08月16日 22:49
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName LangChain4jFunctionCallBackService.java
 */
public interface LangChain4jFunctionCallBackService {

    /**
     * 天气工具回调
     * @param request 工具执行请求
     *
     * @return 返回回调后的内容
     */
    ToolExecutionResultMessage weatherQueryCallBack(ToolExecutionRequest request);

}
