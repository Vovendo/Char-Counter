package edu.t1consulting.charcounter.mapper;

import edu.t1consulting.charcounter.dto.CountResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CountResponseMapperImpl.class})
public class CountResponseMapperTest {
    @Autowired
    private CountResponseMapper countResponseMapper;

    @Test
    public void testLinkedHashMapToValidationResponseDto() {
        LinkedHashMap<Character, Integer> testMap = new LinkedHashMap<>(Map.of('b', 1));

        CountResponseDto responseDto = countResponseMapper.linkedHashMapToValidationResponseDto(testMap);

        Assertions.assertEquals(testMap, responseDto.getCountOfCharacters());
    }
}
