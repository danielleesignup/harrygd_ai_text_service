package com.harrygd.aitextservice.controller;

import org.springframework.web.bind.annotation.*;
import com.harrygd.aitextservice.model.GenerateRequest;
import com.harrygd.aitextservice.model.GenerateResponse;
import com.harrygd.aitextservice.model.GenerateFormat;
import com.harrygd.aitextservice.service.TextGenerationService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/v1")
public class GenerateController {
    
    private final TextGenerationService textGenerationService;

    public GenerateController(TextGenerationService textGenerationService) {
        this.textGenerationService = textGenerationService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }

    @PostMapping("/generate")
    public GenerateResponse generate(@Valid @RequestBody GenerateRequest request) {
        String result = textGenerationService.generateText(request.getKeywords(), request.getFormat());
        return new GenerateResponse(result);
    }
}
