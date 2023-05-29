package com.chatgpt.controller;

import com.chatgpt.pojo.Result;
import com.chatgpt.service.DallEService;
import com.chatgpt.service.DeepAIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("image")
@CrossOrigin(origins = "*")
public class ImageController {
    @Autowired
    private DallEService dallEService;
    @Autowired
    private DeepAIService deepAIService;

    @GetMapping("dalle")
    private Result getDallE(String prompt,
                       @RequestParam(defaultValue = "1") Integer n,
                       @RequestParam(defaultValue = "1024x1024") String size) throws IOException {
        log.info("query for DallE, question:{}",prompt);
        List<String> ans = dallEService.createImage(prompt,n,size);
        log.info("DallE image has been sent");
        return Result.success(ans);
    }

    @GetMapping("deepai")
    private Result deepAICreate(String text, String style) throws IOException {
        log.info("query for DeepAI, question:{}",text);
        String ans = deepAIService.createImage(text, style);
        log.info("DeepAI image has been sent: {}, with style of {}", ans, style);
        return Result.success(ans);
    }
}
