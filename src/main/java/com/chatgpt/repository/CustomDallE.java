package com.chatgpt.repository;

import com.chatgpt.pojo.dallE.DallECreateRequestParameter;
import com.chatgpt.pojo.dallE.DallEResponseParameter;
import com.chatgpt.pojo.dallE.DataItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Component
public class CustomDallE {
    @Value("${API_KEY}")
    private String apiKey;
    private String url = "https://api.openai.com/v1/images/generations";
    private Charset charset = StandardCharsets.UTF_8;

    private int responseTimeout = 10000;

    public List<String> getAnswerByCreate(CloseableHttpClient client, String prompt, Integer n, String size) throws JsonProcessingException {
        HttpPost httpPost = new HttpPost(url);
        //  Setup body
        ObjectMapper objectMapper = new ObjectMapper();
        DallECreateRequestParameter dallECreateRequestParameter = new DallECreateRequestParameter(prompt,n,size);
        HttpEntity httpEntity = new StringEntity(objectMapper.writeValueAsString(dallECreateRequestParameter), charset);
        httpPost.setEntity(httpEntity);
        //  Setup header
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        // setup timeout
        RequestConfig config = RequestConfig
                .custom()
                .setResponseTimeout(responseTimeout, TimeUnit.MILLISECONDS)
                .build();
        httpPost.setConfig(config);
        //  Start request
        try {
            return client.execute(httpPost, response -> {
                String resStr = EntityUtils.toString(response.getEntity(), charset);
                DallEResponseParameter dallEResponseParameter = objectMapper.readValue(resStr, DallEResponseParameter.class);
//                String ans = "";
                List<String> ans = new ArrayList<>();
                for (DataItem dataItem : dallEResponseParameter.getData()){
//                    ans += dataItem.getUrl();
                    ans.add(dataItem.getUrl());
                }
                return ans;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
