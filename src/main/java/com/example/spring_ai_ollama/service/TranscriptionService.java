package com.example.spring_ai_ollama.service;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class TranscriptionService {

    private final TranscriptionModel transcriptionModel;

    public TranscriptionService(TranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }

    public String convertAudioToText(Resource sampleAudio) {

        return transcriptionModel.transcribe(sampleAudio);
    }
}
