package edu.t1consulting.charcounter.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import edu.t1consulting.charcounter.dto.CountResponseDto;
import edu.t1consulting.charcounter.dto.TextDto;
import edu.t1consulting.charcounter.mapper.CountResponseMapper;
import edu.t1consulting.charcounter.mapper.TextMapper;
import edu.t1consulting.charcounter.model.Text;
import edu.t1consulting.charcounter.service.CharCounterService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TextControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private TextController textController;
    @Mock
    private CharCounterService charCounterService;
    @Mock
    private TextMapper textMapper;
    @Mock
    private CountResponseMapper countResponseMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textController).build();
    }

    @Test
    public void testCalculateIdenticalCharacters() throws Exception {
        TextDto requestDto = new TextDto();
        requestDto.setText("bbb");

        Text mappedText = new Text();
        mappedText.setText("bbb");

        LinkedHashMap<Character, Integer> resultMap = new LinkedHashMap<>(Map.of('b', 3));

        CountResponseDto responseDto = new CountResponseDto();
        responseDto.setCountOfCharacters(resultMap);

        when(textMapper.textDtoToText(any(TextDto.class))).thenReturn(mappedText);
        when(charCounterService.calculateIdenticalCharacter(any(Text.class))).thenReturn(resultMap);
        when(countResponseMapper.linkedHashMapToValidationResponseDto(resultMap)).thenReturn(responseDto);

        mockMvc.perform(post("/api/calculateCharacters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"bbb\"}"))
                .andExpect(status().isOk());

        verify(textMapper, times(1)).textDtoToText(any(TextDto.class));
        verify(charCounterService, times(1)).calculateIdenticalCharacter(any(Text.class));
        verify(countResponseMapper, times(1)).linkedHashMapToValidationResponseDto(new LinkedHashMap<>(Map.of('b', 3)));
    }
}
