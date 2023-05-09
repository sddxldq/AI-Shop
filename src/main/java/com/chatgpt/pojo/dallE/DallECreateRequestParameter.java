package com.chatgpt.pojo.dallE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DallECreateRequestParameter {
    String prompt;
    Integer n;
    String size;
}
