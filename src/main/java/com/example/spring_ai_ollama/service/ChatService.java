package com.example.spring_ai_ollama.service;

public interface ChatService {
    String chat(String query, String conversationId);
    public String chatTemplate();
}
