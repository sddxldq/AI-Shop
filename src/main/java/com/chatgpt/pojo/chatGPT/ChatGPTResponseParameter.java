package com.chatgpt.pojo.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponseParameter {
    String id;
    String object;
    String created;
    String model;
    Usage usage;
    List<Choices> choices;
}
