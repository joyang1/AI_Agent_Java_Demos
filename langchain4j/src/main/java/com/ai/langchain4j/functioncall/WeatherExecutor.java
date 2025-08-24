package com.ai.langchain4j.functioncall;

import com.ai.langchain4j.service.LangChain4jFunctionCallBackService;
import com.ai.langchain4j.utils.FunctionCallUtil;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.service.tool.ToolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author tommy
 * @Time 2025年08月16日 17:28
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName WeatherExecutor.java
 */
@Component
public class WeatherExecutor implements ToolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(WeatherExecutor.class);

    @Value("${run.mode}")
    private String runMode;

    @Autowired
    private LangChain4jFunctionCallBackService langChain4jFunctionCallBackService;


    @Override
    public String execute(ToolExecutionRequest toolExecutionRequest, Object o) {
        System.out.println("tool自动执行调用到了");
        try {
            if (Objects.equals("spring", runMode)) {
                logger.info("[custom chat agent] spring 链路");
                return langChain4jFunctionCallBackService.weatherQueryCallBack(toolExecutionRequest).text();
            } else {
                System.out.println("[custom chat agent] cli 链路");
                return FunctionCallUtil.weatherQueryCallBack(toolExecutionRequest).text();
            }
        } catch (Exception e) {
            System.out.println("weatherQuery tool 回调查询失败, error:" + e.getMessage());
        }
        return "当前天气是晴天，30度";
    }
}
