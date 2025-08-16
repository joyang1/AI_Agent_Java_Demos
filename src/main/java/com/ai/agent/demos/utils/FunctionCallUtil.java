package com.ai.agent.demos.utils;

import com.alibaba.fastjson.JSONObject;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.ToolExecutionResultMessage;

/**
 * 工具类
 * 主要为CustomChatAgentCLI使用
 *
 * @author tommy
 * @Time 2025年08月16日 23:32
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName FunctionCallUtil.java
 */
public class FunctionCallUtil {

    private static final String weatherQueryUrl = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%s&extensions=all";

    public static ToolExecutionResultMessage weatherQueryCallBack(ToolExecutionRequest request) {
        System.out.println("weatherQuery tool 回调到了");
        try {
            JSONObject argMap = JSONObject.parseObject(request.arguments());
            String cityName = argMap.get("city").toString();
            //TODO 此处设置高德key
            String key = "";
            String queryUrl = String.format(weatherQueryUrl, key, cityName);
            String weatherString = SimpleOkHttpClient.sendGetRequest(queryUrl);
            return ToolExecutionResultMessage
                    .from(request, extractAmapWeatherInfo(weatherString));
        } catch (Exception e) {
            System.out.println("weatherQuery tool 回调查询失败");
            return ToolExecutionResultMessage
                    .from(request, "今天上海浦东新区的天气是晴天，30度");
        }
    }

    /**
     * 提取高德天气信息
     *
     * @param weatherString
     * @return
     */
    public static String extractAmapWeatherInfo(String weatherString) {
        JSONObject jsonObject = JSONObject.parseObject(weatherString);
        return jsonObject.getString("forecasts");
    }
}
