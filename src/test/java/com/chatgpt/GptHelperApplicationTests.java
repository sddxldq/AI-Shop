package com.chatgpt;

import com.chatgpt.repository.CustomChatGPT;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

@SpringBootTest
class GptHelperApplicationTests {

    @Test
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CustomChatGPT customChatGpt = new CustomChatGPT();
        while (true) {
            System.out.print("\n please type answer(type q to quit)：");
            String question = new Scanner(System.in).nextLine();
            if ("q".equals(question)) break;
            long start = System.currentTimeMillis();
            String answer = customChatGpt.getAnswer(httpClient, question);
            long end = System.currentTimeMillis();
            System.out.println("Time spent：" + (end - start) / 1000.0 + "秒");
            System.out.println(answer);
        }
        httpClient.close();
    }


}
