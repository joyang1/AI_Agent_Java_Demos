package com.ai.agent.demos.service.impl;

import com.ai.agent.demos.functioncall.WeatherExecutor;
import com.ai.agent.demos.service.LangChain4jFunctionCallBackService;
import com.ai.agent.demos.utils.FunctionCallUtil;
import com.ai.agent.demos.utils.SimpleOkHttpClient;
import com.alibaba.fastjson.JSONObject;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author tommy
 * @Time 2025年08月16日 22:50
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName LangChain4jFunctionCallBackServiceImpl.java
 */
@Service("langChain4jFunctionCallBackService")
public class LangChain4jFunctionCallBackServiceImpl implements LangChain4jFunctionCallBackService {

    private static final Logger logger = LoggerFactory.getLogger(LangChain4jFunctionCallBackServiceImpl.class);

    // 高德天气API
    private static final String weatherQueryUrl = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%s&extensions=all";

    @Value("${amap.key}")
    private String aMapKey;

    @Override
    public ToolExecutionResultMessage weatherQueryCallBack(ToolExecutionRequest request) {
        logger.info("weatherQuery tool 回调到了");
        try {
            JSONObject argMap = JSONObject.parseObject(request.arguments());
            String cityName = argMap.get("city").toString();
            String queryUrl = String.format(weatherQueryUrl, aMapKey, cityName);
            String weatherString = SimpleOkHttpClient.sendGetRequest(queryUrl);
            return ToolExecutionResultMessage
                    .from(request, FunctionCallUtil.extractAmapWeatherInfo(weatherString));
        } catch (Exception e) {
            logger.error("weatherQuery tool 回调查询失败", e);
            return ToolExecutionResultMessage
                    .from(request, "今天上海浦东新区的天气是晴天，30度");
        }

    }
}
