

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ContextConfiguration;


import com.harrygd.aitextservice.AiTextServiceApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = AiTextServiceApplication.class)
class GenerateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGenerateWithRealOpenAiService() throws Exception {
        String requestJson = """
            {
              "keywords": ["효율성", "케어"],
              "format": "NOTES"
            }
            """;

        mockMvc.perform(post("/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.generatedText").exists());
    }
}
