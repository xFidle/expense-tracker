package com.example.expenseapi.controller;

import com.example.expenseapi.utils.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIT {
    private final MockMvc mockMvc;
    private final String activeUser = "herkules1@gmail.com";
    private final TokenGenerator gen;

    @Autowired
    public CategoryControllerIT(MockMvc mockMvc, TokenGenerator gen) {
        this.mockMvc = mockMvc;
        this.gen = gen;
    }

    @Test
    void testAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("food"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("transport"));
    }

    @Test
    void testCreateCategory() throws Exception {
        String jsonBody = """
                {
                    "name": "bills"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/category/insert")
                .header("Authorization", "Bearer " + gen.getToken(activeUser))
                .contentType("application/json")
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testUpdateCategory() throws Exception {
        String jsonBody = """
                {
                    "id": 1,
                    "name": "bills"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.put("/category/update/1")
                        .header("Authorization", "Bearer " + gen.getToken(activeUser))
                        .contentType("application/json")
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
