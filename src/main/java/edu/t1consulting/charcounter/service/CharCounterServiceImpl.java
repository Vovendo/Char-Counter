package edu.t1consulting.charcounter.service;

import edu.t1consulting.charcounter.model.Text;
import edu.t1consulting.charcounter.service.counter.CharCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class CharCounterServiceImpl implements CharCounterService {

    private final CharCounter charCounter;

    @Override
    public LinkedHashMap<Character, Integer> calculateIdenticalCharacter(Text text) {
        return charCounter.calculateIdenticalCharacters(text.getText());
    }
}
