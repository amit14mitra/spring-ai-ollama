package com.example.spring_ai_ollama.service;

import com.example.spring_ai_ollama.tools.EmployeeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient chatClient){
        this.chatClient=chatClient;
    }

    @Override
    public String chat(String query, String conversationId) {
        return this.chatClient
                .prompt(query)
                .advisors(advisor -> advisor
                        .param(
                                ChatMemory.CONVERSATION_ID,
                                conversationId
                        ))
                .tools(new EmployeeTool())
                .call()
                .content();
    }

    @Override
    public String chatTemplate() {

        var systemPromptTemplate= SystemPromptTemplate.builder()
                .template("You are a helpful coding assistant. You are an expert in coding.")
                .build();
       var systemMessage=systemPromptTemplate.createMessage();

        var userPromptTemplate= PromptTemplate.builder().template("What is {techName}? tell ma also about {techExample}").build();
        var userMessage=userPromptTemplate.createMessage(Map.of(
                "techName", "Spring",
                "techExample", "spring exception"
        ));


        Prompt prompt = new Prompt(systemMessage,userMessage);

        return this.chatClient.prompt(prompt).call().content();
    }

}
