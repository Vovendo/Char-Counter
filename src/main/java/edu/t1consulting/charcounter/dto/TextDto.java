package edu.t1consulting.charcounter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDto {
    @NotNull(message = "Can't be null")
    @NotEmpty(message = "Can't be empty")
    @NotBlank(message = "Can't be blank")
    @Schema(description = "text", example = "\"aaaaabcccc\"")
    private String text;
}
