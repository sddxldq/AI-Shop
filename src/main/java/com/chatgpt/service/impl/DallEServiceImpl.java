package com.chatgpt.service.impl;

import com.chatgpt.repository.CustomDallE;
import com.chatgpt.service.DallEService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DallEServiceImpl implements DallEService {
    @Autowired
    CustomDallE customDallE;
    @Override
    public List<String> createImage(String prompt, Integer n, String size) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<String> answer = customDallE.getAnswerByCreate(httpClient,prompt,n,size);
        httpClient.close();
        return answer;
    }
}
