package edu.t1consulting.charcounter.service.counter;

import java.util.LinkedHashMap;

public interface CharCounter {

    LinkedHashMap<Character, Integer> calculateIdenticalCharacters(String text);

}
