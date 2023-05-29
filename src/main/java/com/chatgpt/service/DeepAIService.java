package com.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface DeepAIService {
    String createImage(String text, String style) throws IOException;
}
