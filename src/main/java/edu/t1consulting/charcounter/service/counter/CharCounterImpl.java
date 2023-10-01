package edu.t1consulting.charcounter.service.counter;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CharCounterImpl implements CharCounter {

    @Override
    public LinkedHashMap<Character, Integer> calculateIdenticalCharacters(String text) {
        char[] chars = text.toCharArray();
        Arrays.sort(chars);
        HashMap<Character, Integer> resultMap = new HashMap<>();
        int countOfIdenticalCharacters = 0;

        for (int i = 0; i < chars.length; i++) {
            if (i != 0) {
                if (chars[i] == chars[i - 1]) {
                    countOfIdenticalCharacters++;
                } else {
                    resultMap.put(chars[i - 1], ++countOfIdenticalCharacters);
                    countOfIdenticalCharacters = 0;
                }
                if (i == chars.length - 1) {
                    resultMap.put(chars[i], ++countOfIdenticalCharacters);
                }
            }
        }

        return resultMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }
}
