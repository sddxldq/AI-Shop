package com.chatgpt.service;

import java.io.IOException;
import java.util.List;

public interface DallEService {
    List<String> createImage(String prompt, Integer n, String size) throws IOException;
}
