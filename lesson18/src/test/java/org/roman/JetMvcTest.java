package org.roman;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.roman.controller.JetController;
import org.roman.model.Jet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JetMvcTest {

    @Autowired
    private JetController controller;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreate() throws Exception {
        Jet ssj100 = Jet.builder()
                .name("SSJ-100")
                .cruisingSpeed(830.0)
                .length(29.94)
                .maxAltitude(12_200)
                .passengerCapacity(108)
                .build();

        mockMvc.perform(
                post("/jets")
                    .content(objectMapper.writeValueAsString(ssj100))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("SSJ-100"));
    }

    @Test
    void testUpdate() throws Exception {
        Jet ssj100 = Jet.builder()
                .name("SSJ-100")
                .cruisingSpeed(850.0)
                .length(29.94)
                .maxAltitude(12_200)
                .passengerCapacity(108)
                .build();

        mockMvc.perform(
                put("/jets/0")
                        .content(objectMapper.writeValueAsString(ssj100))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cruisingSpeed").value(850.0));
    }
}
