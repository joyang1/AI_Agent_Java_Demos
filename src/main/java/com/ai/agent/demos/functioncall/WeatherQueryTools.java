package com.ai.agent.demos.functioncall;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

/**
 * @author tommy
 * @Time 2025年08月16日 20:59
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName WeatherQueryTool.java
 */
public class WeatherQueryTools {

    @Tool("查询指定城市未来某天的天气信息。如果用户没有指定日期，则默认为今天。")
    public String weatherQuery(@P("The city for which the weather forecast should be returned") String city) {
        System.out.println("tool调用到了");
        return "当前天气是晴天，30度";
    }
}
