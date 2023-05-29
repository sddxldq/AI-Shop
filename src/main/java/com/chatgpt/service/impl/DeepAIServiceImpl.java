package com.chatgpt.service.impl;

import com.chatgpt.repository.CustomDeepAI;
import com.chatgpt.service.DeepAIService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeepAIServiceImpl implements DeepAIService {

    @Autowired
    CustomDeepAI customDeepAI;
    @Override
    public String createImage(String text, String style) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String answer = customDeepAI.getAnswerByCreate(httpClient,text,style);
        httpClient.close();
        return answer;
    }
}
