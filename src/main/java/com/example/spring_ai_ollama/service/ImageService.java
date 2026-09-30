package com.example.spring_ai_ollama.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final ImageModel imageModel;

    public ImageService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public byte[] generateImage(String prompt) {
        logger.info("Inside Image Service !!! - {}",prompt);
        OpenAiImageOptions openAiImageOptions =OpenAiImageOptions.builder()
                .n(1).quality("auto").build();

        ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt, openAiImageOptions));
        logger.info("Image Res---> {}",imageResponse);
        logger.info("Image Res URL---> {}",imageResponse.getResult().getOutput().getUrl());

        String base64Image = imageResponse
                .getResult()
                .getOutput()
                .getB64Json();

        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        return imageBytes;
    }

}
