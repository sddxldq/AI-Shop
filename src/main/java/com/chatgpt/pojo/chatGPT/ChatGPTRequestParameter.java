package com.chatgpt.pojo.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTRequestParameter {
    String model;
    List<ChatGPTMessage> messages = new ArrayList<>();
    public void addMessages(ChatGPTMessage message){
        this.messages.add(message);
    }
}
