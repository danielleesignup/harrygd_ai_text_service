package com.harrygd.aitextservice.controller;

import org.springframework.web.bind.annotation.*;
import com.harrygd.aitextservice.model.GenerateRequest;
import com.harrygd.aitextservice.model.GenerateResponse;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/v1")
public class GenerateController {
    
    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }

    @PostMapping("/generate")
    public GenerateResponse generate(@Valid @RequestBody GenerateRequest request) {
        return new GenerateResponse("This is a placeholder response.");
    }
}
