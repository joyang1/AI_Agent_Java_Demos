package com.ai.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ai.agent", "com.ai.web", "com.ai.langchain4j", "com.ai.springai", "com.ai.common"})
//@EnableAutoConfiguration(exclude = {dev.langchain4j.ollama.spring.AutoConfig.class})
//@Import(com.ai.springai.config.CustomLLMConfig.class) // 显式导入配置类
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}

}
