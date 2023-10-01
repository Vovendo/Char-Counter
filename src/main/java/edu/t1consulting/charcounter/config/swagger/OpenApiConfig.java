package edu.t1consulting.charcounter.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Char Counter",
                description = "Char Counter - application for counting characters in text", version = "1.0.0"
        )
)
public class OpenApiConfig {
}
