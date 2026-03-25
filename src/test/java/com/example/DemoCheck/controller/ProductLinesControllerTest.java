package com.example.DemoCheck.controller;

import com.example.DemoCheck.entity.ProductLine;
import com.example.DemoCheck.repository.ProductLineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductLinesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductLineRepository repository;

    @Test
    void testGetAllProductLines() throws Exception {
        List<ProductLine> list = repository.findAll();
        assert(list.size() >= 0);

        mockMvc.perform(get("/productlines/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void testCreateProductLine() throws Exception {
        String json = """
                {
                    "productLine": "Classic Cars",
                    "textDescription": "desc"
                }
                """;

        mockMvc.perform(post("/productlines/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productLine").value("Classic Cars"));
    }
}