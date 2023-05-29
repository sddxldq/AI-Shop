package com.chatgpt.repository;


import com.chatgpt.pojo.deepAI.DeepAIResponseParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Component
public class CustomDeepAI {
    @Value("${DEEPAI_KEY}")
    private String apiKey;
//    private String url = "https://api.deepai.org/api/";
    private Charset charset = StandardCharsets.UTF_8;

    private int responseTimeout = 100000;

    public String getAnswerByCreate(CloseableHttpClient client, String text, String style) {
        String url = "https://api.deepai.org/api/" + style;
        HttpPost httpPost = new HttpPost(url);
        // Add the 'api-key' header
        httpPost.addHeader("api-key", "8cf2557b-7bc9-478e-b12f-000c8f138329");
        ObjectMapper objectMapper = new ObjectMapper();
        // Create MultipartEntityBuilder
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // Add the text field to the entity
        entityBuilder.addTextBody("text", text, ContentType.TEXT_PLAIN);
        // Build the HttpEntity
        HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
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
                DeepAIResponseParameter deepAIResponseParameter = objectMapper.readValue(resStr, DeepAIResponseParameter.class);
                return deepAIResponseParameter.getOutput_url();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
