package com.harrygd.aitextservice.model;

public class GenerateResponse {
    private String generatedText;

    public GenerateResponse(String generatedText) { this.generatedText = generatedText; }

    public String getGeneratedText() { return generatedText; }
    public void setGeneratedText(String generatedText) { this.generatedText = generatedText; }

} 
