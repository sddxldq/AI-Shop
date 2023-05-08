package com.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface MessageService {
    String chat(String q) throws IOException;
}
