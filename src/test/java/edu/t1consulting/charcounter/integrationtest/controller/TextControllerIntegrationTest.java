package edu.t1consulting.charcounter.integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.t1consulting.charcounter.controller.TextController;
import edu.t1consulting.charcounter.controller.exception.ApiError;
import edu.t1consulting.charcounter.mapper.CountResponseMapper;
import edu.t1consulting.charcounter.mapper.TextMapper;
import edu.t1consulting.charcounter.model.Text;
import edu.t1consulting.charcounter.service.CharCounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TextController.class)
@MockBeans({@MockBean(TextMapper.class), @MockBean(CountResponseMapper.class)})
public class TextControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CharCounterService charCounterService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        when(charCounterService.calculateIdenticalCharacter(new Text("bbb"))).thenReturn(new LinkedHashMap<>(Map.of('b', 3)));
    }

    @Test
    public void whenRequestIsValid_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/calculateCharacters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"bbb\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenRequestIsInvalid_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/calculateCharacters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString();
                    ApiError apiError = objectMapper.readValue(content, ApiError.class);
                    List<String> errors = apiError.getErrors();
                    assertTrue(errors.contains("text: Can't be null"));
                })
                .andDo(print());
    }
}

