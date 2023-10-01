package edu.t1consulting.charcounter.service;

import edu.t1consulting.charcounter.model.Text;

import java.util.LinkedHashMap;

public interface CharCounterService {
    LinkedHashMap<Character, Integer> calculateIdenticalCharacter(Text text);

}
