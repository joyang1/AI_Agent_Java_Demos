package com.ai.agent.demos.functioncall;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;

import java.util.List;

/**
 * @author tommy
 * @Time 2025年08月16日 20:59
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName WeatherQueryTool.java
 */
public class WeatherQueryTools {

    @Tool("查询指定城市未来某天的天气信息。如果用户没有指定日期，则默认为今天。")
    public void weatherQuery(@P(value = "The city for which the weather forecast should be returned", required = true) String city) {
    }

    public static ToolSpecification getWeatherQueryTool() {
        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WeatherQueryTools.class);
        for (ToolSpecification toolSpecification : toolSpecifications) {
            if (toolSpecification.name().equals("weatherQuery")) {
                return toolSpecification;
            }
        }
        return ToolSpecification.builder()
                .name("weatherQuery")
                .description("查询指定城市未来某天的天气信息。如果用户没有指定日期，则默认为今天。")
                .parameters(JsonObjectSchema.builder()
                        .addStringProperty("city", "The city for which the weather forecast should be returned")
                        .build())
                .build();
    }
}
