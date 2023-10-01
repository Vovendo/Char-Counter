package edu.t1consulting.charcounter.mapper;

import edu.t1consulting.charcounter.dto.CountResponseDto;
import org.mapstruct.Mapper;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface CountResponseMapper {

    CountResponseDto linkedHashMapToValidationResponseDto(LinkedHashMap<Character, Integer> countOfCharacters);
}
