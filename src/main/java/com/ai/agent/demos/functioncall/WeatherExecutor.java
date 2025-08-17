package com.ai.agent.demos.functioncall;

import com.ai.agent.demos.utils.FunctionCallUtil;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.service.tool.ToolExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

/**
 * @author tommy
 * @Time 2025年08月16日 17:28
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName WeatherExecutor.java
 */
public class WeatherExecutor implements ToolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(WeatherExecutor.class);

    @Value("${run.mode}")
    private String runMode;


    @Override
    public String execute(ToolExecutionRequest toolExecutionRequest, Object o) {
        System.out.println("tool自动执行调用到了");
        if (Objects.equals("spring", runMode)) {
            logger.info("[custom chat agent] spring 链路");
        } else {
            System.out.println("[custom chat agent] cli 链路");
            return FunctionCallUtil.weatherQueryCallBack(toolExecutionRequest).text();
        }
        return "当前天气是晴天，30度";
    }
}
