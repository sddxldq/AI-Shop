package com.chatgpt.repository;
import com.chatgpt.pojo.chatGPT.ChatGPTMessage;
import com.chatgpt.pojo.chatGPT.ChatGPTRequestParameter;
import com.chatgpt.pojo.chatGPT.ChatGPTResponseParameter;
import com.chatgpt.pojo.chatGPT.Choices;
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
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Component
public class CustomChatGPT {
    @Value("${API_KEY}")
    private String apiKey;
    private String model = "gpt-3.5-turbo";
    private String url = "https://api.openai.com/v1/chat/completions";
    private Charset charset = StandardCharsets.UTF_8;
    private ChatGPTRequestParameter chatGPTRequestParameter = new ChatGPTRequestParameter();

    private int responseTimeout = 100000;

    public String getAnswer(CloseableHttpClient client, String question) throws JsonProcessingException {
        HttpPost httpPost = new HttpPost(url);
        //  Setup body
        ObjectMapper objectMapper = new ObjectMapper();
        chatGPTRequestParameter.setModel(model);
        chatGPTRequestParameter.addMessages(new ChatGPTMessage("user", question));
        HttpEntity httpEntity = new StringEntity(objectMapper.writeValueAsString(chatGPTRequestParameter), charset);
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
                ChatGPTResponseParameter responseParameter = objectMapper.readValue(resStr, ChatGPTResponseParameter.class);
                String ans = "";
                for (Choices choice : responseParameter.getChoices()) {
                    ChatGPTMessage message = choice.getMessage();
                    chatGPTRequestParameter.addMessages(new ChatGPTMessage(message.getRole(), message.getContent()));
                    String s = message.getContent().replaceAll("\n+", "\n");
                    ans += s;
                }
                return ans;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        chatGPTRequestParameter.getMessages().remove(chatGPTRequestParameter.getMessages().size()-1);
        return "Your current network cannot access";
    }


}
