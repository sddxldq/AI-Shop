package com.chatgpt.service.impl;

import com.chatgpt.repository.CustomChatGPT;
import com.chatgpt.service.MessageService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public String chat(String q) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CustomChatGPT customChatGpt = new CustomChatGPT();
        String answer = customChatGpt.getAnswer(httpClient, q);
        httpClient.close();
        return answer;
    }
}
