package com.chatgpt.pojo.dallE;

import com.chatgpt.pojo.chatGPT.Choices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DallEResponseParameter {
    String created;
    List<DataItem> data;
}
