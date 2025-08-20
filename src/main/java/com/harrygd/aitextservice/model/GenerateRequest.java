package com.harrygd.aitextservice.model;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.harrygd.aitextservice.model.GenerateFormat;

public class GenerateRequest {

    @NotNull(message = "format must not be null")
    private GenerateFormat format;

    @NotEmpty(message= "keywords must not be empty")
    @Size(max = 20, message = "keywords cannot exceed 20 items")
    private List<@Size(min = 1, max = 64, message = "each keyword must be 1-64 chars") String> keywords;

    @NotEmpty(message= "format must not be empty")
 
    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }

    public GenerateFormat getFormat(){
         return format; 
    }
    public void setFormat(GenerateFormat format) {
        this.format = format; 
    }
}
