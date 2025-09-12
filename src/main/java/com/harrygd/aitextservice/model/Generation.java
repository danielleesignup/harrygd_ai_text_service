package com.harrygd.aitextservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "generations")
public class Generation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keywords;

    @Column(columnDefinition = "TEXT")
    private String generatedText;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Generation() {}
    public Generation(String keywords, String generatedText) {
        this.keywords = keywords;
        this.generatedText = generatedText;
    }

    // Getters & setters
    public Long getId() { return id; }

    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }

    public String getGeneratedText() { return generatedText; }
    public void setGeneratedText(String generatedText) { this.generatedText = generatedText; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
