package com.ai.web.demos.web;

import com.ai.web.demos.customchat.CustomChatAgent;
import com.ai.common.enums.ChatTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tommy
 * @Time 2025年05月05日 23:57
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName CustomChatAPI.java
 */
@RequestMapping("/api")
@RestController
public class CustomChatAPI {

    private static final Logger logger = LoggerFactory.getLogger(CustomChatAPI.class);

    @Autowired
    private CustomChatAgent customChatAgent;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message") String message, @RequestParam(value = "type") String type) {
        String response = customChatAgent.chat(message, ChatTypeEnum.getByType(type));
        logger.info("chat response: {}", response);
        return response;
    }

}
