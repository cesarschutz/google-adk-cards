package com.example.ai;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import com.example.ai.service.ChatAgentService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(properties = "spring.ai.openai.api-key=test")
@AutoConfigureMockMvc
class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatAgentService chatAgentService;

    @Test
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    void chatEndpointWorks() throws Exception {
        when(chatAgentService.chat(anyString())).thenReturn("ok");
        mockMvc.perform(MockMvcRequestBuilders.post("/chat").content("Teste"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
