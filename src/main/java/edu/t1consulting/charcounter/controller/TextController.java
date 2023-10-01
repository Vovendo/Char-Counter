package edu.t1consulting.charcounter.controller;

import edu.t1consulting.charcounter.dto.TextDto;
import edu.t1consulting.charcounter.dto.CountResponseDto;
import edu.t1consulting.charcounter.mapper.TextMapper;
import edu.t1consulting.charcounter.mapper.CountResponseMapper;
import edu.t1consulting.charcounter.model.Text;
import edu.t1consulting.charcounter.service.CharCounterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TextController {
    private final CharCounterService charCounterService;
    private final TextMapper textMapper;
    private final CountResponseMapper countResponseMapper;

    @Operation(
            summary = "Calculate Identical Characters",
            description = "Accepts any text, and returns the characters and their number, which are in the text, in sorted form"
    )
    @PostMapping("/calculateCharacters")
    public CountResponseDto calculateIdenticalCharacters(@Valid @RequestBody TextDto textDto) {
        Text text = textMapper.textDtoToText(textDto);
        return countResponseMapper.linkedHashMapToValidationResponseDto(charCounterService.calculateIdenticalCharacter(text));
    }
}
