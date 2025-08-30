


import com.fasterxml.jackson.databind.ObjectMapper;
import com.harrygd.aitextservice.AiTextServiceApplication;
import com.harrygd.aitextservice.controller.GenerateController;
import com.harrygd.aitextservice.model.GenerateFormat;
import com.harrygd.aitextservice.model.GenerateRequest;
import com.harrygd.aitextservice.service.TextGenerationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenerateController.class)
@Import(GenerateControllerTest.MockServiceConfig.class)
@ContextConfiguration(classes = AiTextServiceApplication.class)
class GenerateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TextGenerationService textGenerationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGenerateWithValidInput() throws Exception {
        // Arrange
        GenerateRequest request = new GenerateRequest();
        request.setKeywords(List.of("효율성", "케어"));
        request.setFormat(GenerateFormat.NOTES);

        String expectedOutput = "1. 요양보호사는 효율성에 대해 논의했다.";

        when(textGenerationService.generateText(anyList(), any())).thenReturn(expectedOutput);

        // Act & Assert
        mockMvc.perform(post("/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.generatedText").value(expectedOutput));
    }

    @Test
    void testGenerateWithEmptyKeywords() throws Exception {
        String requestJson = """
            {
            "keywords": [],
            "format": "NOTES"
            }
            """;
        mockMvc.perform(post("/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("keywords must not be empty"));
    }


    @Test
    void testGenerateWithMissingFormat() throws Exception {
        String requestJson = """
            {
            "keywords": ["효율성", "케어"]
            }
            """;

        mockMvc.perform(post("/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("format must not be null"));
    }
    @TestConfiguration
    static class MockServiceConfig {
        @Bean
        public TextGenerationService textGenerationService() {
            return mock(TextGenerationService.class);
        }
    }
}
