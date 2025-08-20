package com.harrygd.aitextservice.model;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class GenerateRequest {

    @NotEmpty(message= "keywords must not be empty")
    @Size(max = 20, message = "keywords cannot exceed 20 items")
    private List<@Size(min = 1, max = 64, message = "each keyword must be 1-64 chars") String> keywords;

    @NotEmpty(message= "format must not be empty")
    private String format; //"notes" or "dialogue"
 
    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
