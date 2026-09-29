package com.example.spring_ai_ollama.controller;

import com.example.spring_ai_ollama.service.ChatService;
import com.example.spring_ai_ollama.service.TranscriptionService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private ChatService chatService;
    private TranscriptionService transcriptionService;

    public ChatController(ChatService chatService, TranscriptionService transcriptionService){
        this.chatService=chatService;
        this.transcriptionService=transcriptionService;
    }

    @GetMapping("/get-query")
    public ResponseEntity<String> getResponse(@RequestParam(value="query", required = true) String query,
                                              @RequestParam String conversationId){
        System.out.println("Inside chat response API");
        System.out.println("==============================================");
        var res=chatService.chat(query, conversationId);
        System.out.println("response--> "+res);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/system-user-query")
    public ResponseEntity<String> getCustomResponse(){
        System.out.println("Inside chat custom response API");
        System.out.println("==============================================");
        var res=chatService.chatTemplate();
        System.out.println("response--> "+res);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/transcript-audio")
    public ResponseEntity<String> transcriptAudio(@Value("${classPath:akm-sample-audio.mp4}")
                                                  Resource sampleAudio){
        System.out.println("Inside audio transcription controller!!");

        String text=transcriptionService.convertAudioToText(sampleAudio);
        return ResponseEntity.ok(text);
    }

}
