package com.harrygd.aitextservice.controller;

import org.springframework.web.bind.annotation.*;
import com.harrygd.aitextservice.model.GenerateRequest;
import com.harrygd.aitextservice.model.GenerateResponse;
import com.harrygd.aitextservice.model.GenerateFormat;
import com.harrygd.aitextservice.model.Generation;
import com.harrygd.aitextservice.repository.GenerationRepository;
import com.harrygd.aitextservice.service.TextGenerationService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/v1")
public class GenerateController {
    
    private final TextGenerationService textGenerationService;
    private final GenerationRepository generationRepository;

    public GenerateController(TextGenerationService textGenerationService, GenerationRepository generationRepository) {
        this.textGenerationService = textGenerationService;
        this.generationRepository = generationRepository;

    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }

    @PostMapping("/generate")
    public GenerateResponse generate(@Valid @RequestBody GenerateRequest request) {
        // 1. Generate Text
        String result = textGenerationService.generateText(request.getKeywords(), request.getFormat());
        
        // // 2. Save to Postgres
        Generation g = new Generation(String.join(",", request.getKeywords()), result);
        System.out.println(">>> Saving to DB: " + result);
        generationRepository.save(g);
        
        // 3. Return response
        return new GenerateResponse(result);
    }
}
