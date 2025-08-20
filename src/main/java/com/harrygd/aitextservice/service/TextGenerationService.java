package com.harrygd.aitextservice.service;

import com.harrygd.aitextservice.model.GenerateFormat;

import java.util.List;

public interface TextGenerationService {
    String generateText(List<String> keywords, GenerateFormat format);
}
