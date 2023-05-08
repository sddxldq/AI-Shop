package com.chatgpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForTest {

    @Value("${API_KEY}")
    private String myApiKey;


    @GetMapping("/test")
    public String doSomething() {
        return myApiKey;
    }
}