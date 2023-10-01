package edu.t1consulting.charcounter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountResponseDto {
    @Schema(description = "Count of characters", example = "\"a\": 5, \"c\": 4, \"b\": 1")
    private LinkedHashMap<Character, Integer> countOfCharacters;
}
