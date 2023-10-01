package edu.t1consulting.charcounter.service.validation;

import edu.t1consulting.charcounter.service.counter.CharCounter;
import edu.t1consulting.charcounter.service.counter.CharCounterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CharCounterTest {
    @ParameterizedTest
    @MethodSource("testCounterSource")
    void testCalculateIdenticalCharacters(ArgumentsAccessor accessor) {
        List<Object> args = accessor.toList();
        String text = (String) args.get(0);
        LinkedHashMap<Character, Integer> expectedResult = new LinkedHashMap<>();

        for(int i = 1; i < args.size(); i++) {
            String result = (String) args.get(i);
            Character charResult = result.charAt(1);
            Integer intResult = Integer.parseInt(result.substring(5));
            expectedResult.put(charResult, intResult);
        }

        CharCounter charCounter = new CharCounterImpl();
        LinkedHashMap<Character, Integer> actualResult = charCounter.calculateIdenticalCharacters(text);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    static Stream<Arguments> testCounterSource() {
        return Stream.of(
                arguments(
                        "aaa",
                        "\"a\": 3"
                ),
                arguments(
                        "aabb",
                        "\"a\": 2",
                        "\"b\": 2"
                ),
                arguments(
                        "1 hrr",
                        "\"r\": 2",
                        "\" \": 1",
                        "\"1\": 1",
                        "\"h\": 1"
                )
        );
    }
}
