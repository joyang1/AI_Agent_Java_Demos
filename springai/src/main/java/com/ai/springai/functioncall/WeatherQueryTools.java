package com.ai.springai.functioncall;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * @author tommy
 * @Time 2025年08月30日 18:30
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName WeatherQueryTools.java
 */
public class WeatherQueryTools {

    @Tool(description = "查询指定城市未来某天的天气信息。如果用户没有指定日期，则默认为今天。")
    public void weatherQuery(@ToolParam(description="The city for which the weather forecast should be returned")String city) {
    }

}
