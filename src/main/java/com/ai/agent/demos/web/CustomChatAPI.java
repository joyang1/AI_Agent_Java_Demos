package com.ai.agent.demos.web;

import com.ai.agent.demos.customchat.CustomChatAgent;
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

    @Autowired
    private CustomChatAgent customChatAgent;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message") String message) {

        String response = customChatAgent.chat(message);
        System.out.println("response:\n" + response);
        return response;
    }

}
