package com.ai.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		org.springframework.ai.autoconfigure.ollama.OllamaAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.ai.agent", "com.ai.web", "com.ai.langchain4j", "com.ai.springai", "com.ai.common"})
//@EnableAutoConfiguration(exclude = {dev.langchain4j.ollama.spring.AutoConfig.class})
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}

}
