package com.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface DallECreateService {
    String createImage(String prompt, Integer n, String size) throws IOException;
}
