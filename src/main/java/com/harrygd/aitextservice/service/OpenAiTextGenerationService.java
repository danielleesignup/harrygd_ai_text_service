package com.harrygd.aitextservice.service;

import com.harrygd.aitextservice.model.GenerateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;


@Primary
@Service
public class OpenAiTextGenerationService implements TextGenerationService{
    
    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    @Override
    public String generateText(List<String> keywords, GenerateFormat format) {
        try {
            //1. Build prompt
            String prompt = buildPrompt(keywords, format);

            //2. Create JSON payload
            JSONObject payload = new JSONObject();
            payload.put("model", "gpt-3.5-turbo");

            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "user").put("content", prompt));
            payload.put("messages", messages);

            //3. 
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENAI_ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

                 // 4. Send request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Parse result
            JSONObject json = new JSONObject(response.body());
            String text = json
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
            System.out.println("Generated GPT output:\n" + text);
            return text.trim();  

        } catch (Exception e) {
            return "Failed to generate text: " + e.getMessage();
        }
    }

    private String buildPrompt(List<String> keywords, GenerateFormat format) {
        String baseInstruction;

        if (format == GenerateFormat.NOTES) {
            baseInstruction = """
                You are generating internal meeting notes for a nursing home staff meeting.
                The setting involves caregivers, the nursing team leader (간호팀장), and the director (대표).
                Use a clear, concise, and formal tone. Reflect action items and discussion points accurately.
            """;
        } else {
            baseInstruction = """
                Simulate a professional nursing home meeting in dialogue format.
                The participants are 요양보호사 (caregiver), 간호팀장 (nurse team lead), and 대표 (director).
                Use formal Korean or English tone (based on keywords), with distinct speaker labels and realistic content.
            """;
        }

        String keywordPart = "Keywords: " + String.join(", ", keywords);

        return baseInstruction + "\n\n" + keywordPart;
    }
}
