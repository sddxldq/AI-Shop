package com.chatgpt.controller;

import com.chatgpt.pojo.Result;
import com.chatgpt.service.DallECreateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    private DallECreateService dallECreateService;

    @GetMapping
    private Result get(String prompt,
                       @RequestParam(defaultValue = "1") Integer n,
                       @RequestParam(defaultValue = "1024x1024") String size) throws IOException {
        log.info("query answer for question:{}",prompt);
        String ans = dallECreateService.createImage(prompt,n,size);
        return Result.success(ans);
    }
}
