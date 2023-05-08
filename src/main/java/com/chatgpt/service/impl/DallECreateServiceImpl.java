package com.chatgpt.service.impl;

import com.chatgpt.repository.CustomDallE;
import com.chatgpt.service.DallECreateService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DallECreateServiceImpl implements DallECreateService {
    @Autowired
    CustomDallE customDallE;
    @Override
    public String createImage(String prompt, Integer n, String size) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String answer = customDallE.getAnswer(httpClient,prompt,n,size);
        httpClient.close();
        return answer;
    }
}
