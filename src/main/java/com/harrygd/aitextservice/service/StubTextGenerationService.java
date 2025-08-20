package com.harrygd.aitextservice.service;

import com.harrygd.aitextservice.model.GenerateFormat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StubTextGenerationService implements TextGenerationService {

    @Override
    public String generateText(List<String> keywords, GenerateFormat format) {
        return "Stubbed output for format: " + format + " with keywords: " + String.join(", ", keywords);
    }
}
